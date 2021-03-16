package ApplicationLogic;

public class PeriodicActivity extends Activity {

    public static Activity makePeriodicActivity(
            String name,
            String description,
            String valueInClocks,
            String importance,
            String repetitionLengthInSec,
            String isDuty
    ) {
        return new PeriodicActivity(
                name,
                description,
                Integer.parseInt(valueInClocks),
                Integer.parseInt(importance),
                Integer.parseInt(repetitionLengthInSec),
                Boolean.parseBoolean(isDuty)
        );
    }

    private PeriodicActivity(
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
