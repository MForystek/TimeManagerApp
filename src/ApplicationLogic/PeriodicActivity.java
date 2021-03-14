package ApplicationLogic;

public class PeriodicActivity extends Activity {

    public PeriodicActivity(
            String name,
            String description,
            int valueInClocks,
            int importance,
            int activityDurationInSec,
            boolean isDuty
    ) {
        super(
                name,
                description,
                valueInClocks,
                importance,
                activityDurationInSec,
                isDuty
        );
    }

    @Override
    public ActivitySegment getNextSegment(int occurrenceTime){
        return new ActivitySegment(
                getName(),
                getActivityDurationInSec(),
                getValueInClocks(),
                occurrenceTime
        );
    }
}
