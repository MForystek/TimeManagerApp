package ApplicationLogic;

import java.time.LocalDate;

public class OneTimeActivity extends Activity {
    LocalDate deadline;

    public static Activity makeOneTimeActivity(
            String name,
            String description,
            String valueInClocks,
            String importance,
            String repetitionLengthInSec,
            String isDuty,
            LocalDate date
    ) {
        return new OneTimeActivity(
                name,
                description,
                Integer.parseInt(valueInClocks),
                Integer.parseInt(importance),
                Integer.parseInt(repetitionLengthInSec),
                Boolean.parseBoolean(isDuty),
                date
        );
    }

    private OneTimeActivity(
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

