package ApplicationLogic;

public class PeriodicActivity extends Activity {
    //variable lengthInSec for this class is length of each repetition

    public PeriodicActivity(String name, String description, int repetitionLengthInSec, int valueInClocks, short importance) {
        super(name, description, repetitionLengthInSec, valueInClocks, importance);
    }

    @Override
    public ActivitySegment getNextSegment(){
        return new ActivitySegment(getRepetitionLengthInSec(), getValueInClocks());
    }
}
