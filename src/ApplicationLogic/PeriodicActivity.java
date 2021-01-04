package ApplicationLogic;

public class PeriodicActivity extends Activity {

    public PeriodicActivity(String name, String description, int valueInClocks, short importance, int repetitionLengthInSec,boolean isDuty) {
        super(name, description, valueInClocks, importance, repetitionLengthInSec, isDuty);
    }

    @Override
    public ActivitySegment getNextSegment(){
        return new ActivitySegment(getName(), getRepetitionLengthInSec(), getValueInClocks());
    }
}
