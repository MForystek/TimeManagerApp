package ApplicationLogic;

import java.util.Date;

public class ProjectActivity extends Activity {

    private short totalLengthInSec;
    Date deadline;

    public ProjectActivity(String name, String description, int valueInClocks, short importance, int repetitionLengthInSec, boolean isDuty, short totalLengthInSec, Date deadline) {
        super(name, description, valueInClocks, importance, repetitionLengthInSec, isDuty);
        this.totalLengthInSec = totalLengthInSec;
        this.deadline = deadline;
    }

    @Override
    public ActivitySegment getNextSegment(){
        float factor = (float) totalLengthInSec /(float) getRepetitionLengthInSec();
        return new ActivitySegment(getName(), totalLengthInSec, (int)(factor*getValueInClocks()));
    }

    //getters & setters
    public short getTotalLengthInSec() {
        return totalLengthInSec;
    }

    public void setTotalLengthInSec(short totalLengthInSec) {
        this.totalLengthInSec = totalLengthInSec;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
