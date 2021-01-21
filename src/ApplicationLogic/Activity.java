package ApplicationLogic;

public abstract class Activity {

    private String name;
    private String description;
    private int valueInClocks;
    private int importance;
    private int repetitionLengthInSec;
    private boolean duty;

    public Activity(String name, String description, int valueInClocks, int importance, int repetitionLengthInSec, boolean duty) {
        this.name = name;
        this.description = description;
        this.valueInClocks = valueInClocks;
        this.importance = importance;
        this.repetitionLengthInSec = repetitionLengthInSec;
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

    public int getRepetitionLengthInSec() {
        return repetitionLengthInSec;
    }

    public void setRepetitionLengthInSec(int repetitionLengthInSec) {
        this.repetitionLengthInSec = repetitionLengthInSec;
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
