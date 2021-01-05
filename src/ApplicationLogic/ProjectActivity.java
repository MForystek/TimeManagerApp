package ApplicationLogic;

import java.time.LocalDate;
import java.util.Date;

public class ProjectActivity extends Activity {

    private int totalLengthInSec;
    LocalDate deadline;

    public ProjectActivity(String name, String description, int valueInClocks, short importance, int repetitionLengthInSec, boolean isDuty, int totalLengthInSec, LocalDate deadline) {
        super(name, description, valueInClocks, importance, repetitionLengthInSec, isDuty);
        this.totalLengthInSec = totalLengthInSec;
        this.deadline = deadline;
    }

    @Override
    public ActivitySegment getNextSegment(int occurrenceTime){
        float factor = (float) totalLengthInSec /(float) getRepetitionLengthInSec();
        return new ActivitySegment(getName(), totalLengthInSec, (int)(factor*getValueInClocks()), occurrenceTime);
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
