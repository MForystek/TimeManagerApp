package ApplicationLogic;

import java.time.LocalDate;

public class ProjectActivity extends Activity {

    private int totalDurationInSec;
    LocalDate deadline;

    public ProjectActivity(
            String name,
            String description,
            int valueInClocks,
            int importance,
            int activityDurationInSec,
            boolean isDuty,
            int totalDurationInSec,
            LocalDate deadline
    ) {
        super(
                name,
                description,
                valueInClocks,
                importance,
                activityDurationInSec,
                isDuty
        );
        this.totalDurationInSec = totalDurationInSec;
        this.deadline = deadline;
    }

    @Override
    public ActivitySegment getNextSegment(int occurrenceTime){
        double valueInClocksCoefficient =  ((double) getActivityDurationInSec()) / totalDurationInSec;
        int segmentValueInClocks = (int)valueInClocksCoefficient * getValueInClocks();
        return new ActivitySegment(
                getName(),
                getActivityDurationInSec(),
                segmentValueInClocks,
                occurrenceTime);
    }

    public void subtractFromTotalDuration(int durationToSubtract){
        this.totalDurationInSec -= durationToSubtract;
    }

    //getters & setters
    public int getTotalDurationInSec() {
        return totalDurationInSec;
    }

    public void setTotalDurationInSec(int totalDurationInSec) {
        this.totalDurationInSec = totalDurationInSec;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
