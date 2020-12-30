package ApplicationLogic;

import java.util.Date;

public class ProjectActivity extends Activity {
    // variable lengthInSec for this class is length of whole project

    private short totalLengthInSec;
    Date deadline;

    public ProjectActivity(String name, String description, int repetitionLengthInSec, int valueInClocks, short importance, short totalLengthInSec, Date deadline) {
        super(name, description, repetitionLengthInSec, valueInClocks, importance);
        this.totalLengthInSec = totalLengthInSec;
        this.deadline = deadline;
    }

    @Override
    public ActivitySegment getNextSegment(){
        float factor = (float) totalLengthInSec /(float) getRepetitionLengthInSec();
        return new ActivitySegment(totalLengthInSec, (int)(factor*getValueInClocks()));
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
