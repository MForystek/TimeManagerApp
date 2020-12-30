package ApplicationLogic;

import java.util.Date;

public class OneTimeActivity extends Activity {
    //variable lengthInSec for this class is its whole length
    Date deadline;

    public OneTimeActivity(String name, String description, int repetitionLengthInSec, int valueInClocks, short importance, Date deadline) {
        super(name, description, valueInClocks, repetitionLengthInSec, importance);
        this.deadline = deadline;
    }

    @Override
    public ActivitySegment getNextSegment(){
        return new ActivitySegment(getRepetitionLengthInSec(), getValueInClocks());
    }

    //getters & setters
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}

