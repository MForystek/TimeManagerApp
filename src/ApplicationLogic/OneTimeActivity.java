package ApplicationLogic;

import java.time.LocalDate;

public class OneTimeActivity extends Activity {
    LocalDate deadline;

    public OneTimeActivity(String name, String description, int valueInClocks, int importance, int repetitionLengthInSec, boolean isDuty, LocalDate deadline) {
        super(name, description, valueInClocks, importance, repetitionLengthInSec, isDuty);
        this.deadline = deadline;
    }

    @Override
    public ActivitySegment getNextSegment(int occurrenceTime){
        return new ActivitySegment(getName(), getRepetitionLengthInSec(), getValueInClocks(), occurrenceTime);
    }

    //getters & setters
    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}

