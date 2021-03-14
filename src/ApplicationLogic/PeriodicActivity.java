package ApplicationLogic;

public class PeriodicActivity extends Activity {

    public PeriodicActivity(
            String name,
            String description,
            int valueInClocks,
            int importance,
            int segmentDurationInSec,
            boolean isDuty
    ) {
        super(
                name,
                description,
                valueInClocks,
                importance,
                segmentDurationInSec,
                isDuty
        );
    }

    @Override
    public ActivitySegment getNextSegment(int occurrenceTime){
        return new ActivitySegment(
                getName(),
                getSegmentDurationInSec(),
                getValueInClocks(),
                occurrenceTime
        );
    }
}
