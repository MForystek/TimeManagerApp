package ApplicationLogic;

public abstract class Activity {

    private String name;
    private String description;
    private int valueInClocks;
    private short importance;
    private int repetitionLengthInSec;
    private boolean isDuty;

    public Activity(String name, String description, int valueInClocks, short importance, int repetitionLengthInSec, boolean isDuty) {
        this.name = name;
        this.description = description;
        this.valueInClocks = valueInClocks;
        this.importance = importance;
        this.repetitionLengthInSec = repetitionLengthInSec;
        this.isDuty = isDuty;
    }

    public abstract ActivitySegment getNextSegment();

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
