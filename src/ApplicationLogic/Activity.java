package ApplicationLogic;

public abstract class Activity {

    private int valueInClocks;
    private String name;
    private String description;
    private int totalLengthInSec;
    private short importance;
    private boolean mustHappen;

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

    public int getTotalLengthInSec() {
        return totalLengthInSec;
    }

    public void setTotalLengthInSec(int totalLengthInSec) {
        this.totalLengthInSec = totalLengthInSec;
    }

    public short getImportance() {
        return importance;
    }

    public void setImportance(short importance) {
        this.importance = importance;
    }

    public boolean isMustHappen() {
        return mustHappen;
    }

    public void setMustHappen(boolean mustHappen) {
        this.mustHappen = mustHappen;
    }
}
