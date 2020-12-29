package ApplicationLogic;

import java.util.Date;

public class ProjectActivity extends Activity {
    private short lengthOfSegmentSec;
    Date deadline;

    public ProjectActivity(int valueInClocks, String name, String description, int totalLengthInSec, short importance, boolean mustHappen, short lengthOfSegmentSec, Date deadline) {
        super(valueInClocks, name, description, totalLengthInSec, importance, mustHappen);
        this.lengthOfSegmentSec = lengthOfSegmentSec;
        this.deadline = deadline;
    }
}
