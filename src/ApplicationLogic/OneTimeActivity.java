package ApplicationLogic;

import java.util.Date;

public class OneTimeActivity extends Activity {
    //variable lengthInSec for this class is its whole length
    Date deadline;

    public OneTimeActivity(String name, String description, int lengthInSec, int valueInClocks, short importance, Date deadline) {
        super(name, description, valueInClocks, lengthInSec, importance);
        this.deadline = deadline;
    }

    public ActivitySegment getNextSegment(){
        return new ActivitySegment(getLengthInSec(), getValueInClocks());
    }

    //getters & setters
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}

