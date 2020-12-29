package ApplicationLogic;

public class PeriodicActivity extends Activity {
    private short lengthOfSegmentSec;

    public PeriodicActivity(int valueInClocks, String name, String description, int totalLengthInSec, short importance, boolean mustHappen, short lengthOfSegmentSec) {
        super(valueInClocks, name, description, totalLengthInSec, importance, mustHappen);
        this.lengthOfSegmentSec = lengthOfSegmentSec;
    }
}
