package ApplicationLogic;

import java.util.Date;

public class OneTimeActivity extends Activity {
    Date deadline;

    public OneTimeActivity(String name, String description, int valueInClocks, short importance, int repetitionLengthInSec, boolean isDuty, Date deadline) {
        super(name, description, valueInClocks, importance, repetitionLengthInSec, isDuty);
        this.deadline = deadline;
    }

    @Override
    public ActivitySegment getNextSegment(){
        return new ActivitySegment(getName(), getRepetitionLengthInSec(), getValueInClocks());
    }

    //getters & setters
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}

