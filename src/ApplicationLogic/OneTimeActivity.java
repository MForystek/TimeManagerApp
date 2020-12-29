package ApplicationLogic;

import java.util.Date;

public class OneTimeActivity extends Activity {
    //variable lengthInSec for this class is its whole length
    Date deadline;

    public OneTimeActivity(int valueInClocks, String name, String description, int lengthInSec, short importance, Date deadline) {
        super(valueInClocks, name, description, lengthInSec, importance);
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

