package ApplicationLogic;

import java.util.Date;

public class ProjectActivity extends Activity {
    // variable lengthInSec for this class is length of whole project

    private short lengthOfSegmentInSec;
    Date deadline;

    public ProjectActivity(String name, String description, int lengthInSec, int valueInClocks, short importance, short lengthOfSegmentInSec, Date deadline) {
        super(name, description, lengthInSec, valueInClocks, importance);
        this.lengthOfSegmentInSec = lengthOfSegmentInSec;
        this.deadline = deadline;
    }

    public ActivitySegment getNextSegment(){
        float factor = (float)lengthOfSegmentInSec/(float)getLengthInSec();
        return new ActivitySegment(lengthOfSegmentInSec, (int)(factor*getValueInClocks()));
    }

    //getters & setters
    public short getLengthOfSegmentInSec() {
        return lengthOfSegmentInSec;
    }

    public void setLengthOfSegmentInSec(short lengthOfSegmentInSec) {
        this.lengthOfSegmentInSec = lengthOfSegmentInSec;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
