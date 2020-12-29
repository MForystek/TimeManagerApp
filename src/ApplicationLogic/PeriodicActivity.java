package ApplicationLogic;

public class PeriodicActivity extends Activity {
    //variable lengthInSec for this class is length of each repetition

    public PeriodicActivity(int valueInClocks, String name, String description, int lengthInSec, short importance) {
        super(valueInClocks, name, description, lengthInSec, importance);
    }

    public ActivitySegment getNextSegment(){
        return new ActivitySegment(getLengthInSec(), getValueInClocks());
    }
}
