package ApplicationLogic;

public class PeriodicActivity extends Activity {
    //variable lengthInSec for this class is length of each repetition

    public PeriodicActivity(String name, String description, int lengthInSec, int valueInClocks, short importance) {
        super(name, description, lengthInSec, valueInClocks, importance);
    }

    public ActivitySegment getNextSegment(){
        return new ActivitySegment(getLengthInSec(), getValueInClocks());
    }
}
