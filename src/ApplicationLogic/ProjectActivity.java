package ApplicationLogic;

import java.time.LocalDate;

public class ProjectActivity extends Activity {
    private int totalDurationInSec;
    LocalDate deadline;

    public static Activity makeProjectActivity(
            String name,
            String description,
            String valueInClocks,
            String importance,
            String repetitionLengthInSec,
            String isDuty,
            String totalLengthInSec,
            LocalDate date
    ) {
        return new ProjectActivity(
                name,
                description,
                Integer.parseInt(valueInClocks),
                Integer.parseInt(importance),
                Integer.parseInt(repetitionLengthInSec),
                Boolean.parseBoolean(isDuty),
                Integer.parseInt(totalLengthInSec),
                date
        );
    }

    private ProjectActivity(
            String name,
            String description,
            int valueInClocks,
            int importance,
            int segmentDurationInSec,
            boolean isDuty,
            int totalDurationInSec,
            LocalDate deadline
    ) {
        super(
                name,
                description,
                valueInClocks,
                importance,
                segmentDurationInSec,
                isDuty
        );
        this.totalDurationInSec = totalDurationInSec;
        this.deadline = deadline;
    }

    @Override
    public ActivitySegment getNextSegment(int occurrenceTime){
        //TODO The way of calculating a value of segments needs to be improved and repaired
        //TODO Now it doesn't work and user can get less clocks than the activity is worth!!!
        int segmentValueInClocks = getSegmentDurationInSec() * getValueInClocks() / totalDurationInSec;
        return new ActivitySegment(
                getName(),
                getSegmentDurationInSec(),
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
