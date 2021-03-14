package ApplicationLogic;

public abstract class Activity {

    private String name;
    private String description;
    private int valueInClocks;
    private int importance;
    private int activityDurationInSec;
    private boolean duty;

    public Activity(String name, String description, int valueInClocks, int importance, int activityDurationInSec, boolean duty) {
        this.name = name;
        this.description = description;
        this.valueInClocks = valueInClocks;
        this.importance = importance;
        this.activityDurationInSec = activityDurationInSec;
        this.duty = duty;
    }

    public abstract ActivitySegment getNextSegment(int occurrenceTime);

    //getters & setters
    public int getValueInClocks() {
        return valueInClocks;
    }

    public void setValueInClocks(int valueInClocks) {
        this.valueInClocks = valueInClocks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getActivityDurationInSec() {
        return activityDurationInSec;
    }

    public void setActivityDurationInSec(int activityDurationInSec) {
        this.activityDurationInSec = activityDurationInSec;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public boolean isDuty() {
        return duty;
    }
}
