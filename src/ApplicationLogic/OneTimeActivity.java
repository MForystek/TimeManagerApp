package ApplicationLogic;

import java.time.LocalDate;

public class OneTimeActivity extends Activity {
    LocalDate deadline;

    public OneTimeActivity(
            String name,
            String description,
            int valueInClocks,
            int importance,
            int activityDurationInSec,
            boolean isDuty, LocalDate deadline
    ) {
        super(
                name,
                description,
                valueInClocks,
                importance,
                activityDurationInSec,
                isDuty
        );
        this.deadline = deadline;
    }

    @Override
    public ActivitySegment getNextSegment(int occurrenceTime){
        return new ActivitySegment(
                getName(),
                getActivityDurationInSec(),
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

