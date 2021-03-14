package ApplicationLogic;

import java.time.LocalDate;

public class OneTimeActivity extends Activity {
    LocalDate deadline;

    public OneTimeActivity(
            String name,
            String description,
            int valueInClocks,
            int importance,
            int segmentDurationInSec,
            boolean isDuty, LocalDate deadline
    ) {
        super(
                name,
                description,
                valueInClocks,
                importance,
                segmentDurationInSec,
                isDuty
        );
        this.deadline = deadline;
    }

    @Override
    public ActivitySegment getNextSegment(int occurrenceTime){
        return new ActivitySegment(
                getName(),
                getSegmentDurationInSec(),
                getValueInClocks(),
                occurrenceTime
        );
    }

    //getters & setters
    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}

