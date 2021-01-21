package ApplicationLogic;

import java.time.LocalDate;

public class ProjectActivity extends Activity {

    private int totalLengthInSec;
    LocalDate deadline;

    public ProjectActivity(String name, String description, int valueInClocks, int importance, int repetitionLengthInSec, boolean isDuty, int totalLengthInSec, LocalDate deadline) {
        super(name, description, valueInClocks, importance, repetitionLengthInSec, isDuty);
        this.totalLengthInSec = totalLengthInSec;
        this.deadline = deadline;
    }

    @Override
    public ActivitySegment getNextSegment(int occurrenceTime){
        double factor =  ((double) getRepetitionLengthInSec()) / ((double) totalLengthInSec);
        return new ActivitySegment(getName(), getRepetitionLengthInSec(), (int)(factor*getValueInClocks()), occurrenceTime);
    }

    public void subtractLength(int length){
        this.totalLengthInSec -= length;
    }

    //getters & setters
    public int getTotalLengthInSec() {
        return totalLengthInSec;
    }

    public void setTotalLengthInSec(int totalLengthInSec) {
        this.totalLengthInSec = totalLengthInSec;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
