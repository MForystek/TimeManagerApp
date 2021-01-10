package ApplicationLogic;

public class ActivitySegmentFactory {
    public static ActivitySegment makeActivitySegment(
            String parentName,
            String lengthInSec,
            String valueInClocks,
            String occurrenceTime
    ) {
        return new ActivitySegment(
                parentName,
                Integer.parseInt(lengthInSec),
                Integer.parseInt(valueInClocks),
                Integer.parseInt(occurrenceTime)
        );
    }
}
