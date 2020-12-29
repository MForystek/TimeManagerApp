package ApplicationLogic;

import java.util.Date;

public class OneTimeActivity extends Activity {
    Date deadline;

    public OneTimeActivity(int valueInClocks, String name, String description, int totalLengthInSec, short importance, boolean mustHappen, Date deadline) {
        super(valueInClocks, name, description, totalLengthInSec, importance, mustHappen);
        this.deadline = deadline;
    }
}
