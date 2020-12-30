package ApplicationLogic;

public abstract class Activity {

    private int valueInClocks;
    private String name;
    private String description;
    private short importance;
    private int repetitionLengthInSec;

    public Activity(String name, String description, int repetitionLengthInSec, int valueInClocks, short importance) {
        this.valueInClocks = valueInClocks;
        this.name = name;
        this.description = description;
        this.repetitionLengthInSec = repetitionLengthInSec;
        this.importance = importance;
    }

    abstract public ActivitySegment getNextSegment();

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

    public short getImportance() {
        return importance;
    }

    public void setImportance(short importance) {
        this.importance = importance;
    }
}
