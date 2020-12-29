package ApplicationLogic;

public abstract class Activity {

    private int valueInClocks;
    private String name;
    private String description;
    private short importance;
    private int lengthInSec;

    public Activity(String name, String description, int lengthInSec, int valueInClocks, short importance) {
        this.valueInClocks = valueInClocks;
        this.name = name;
        this.description = description;
        this.lengthInSec = lengthInSec;
        this.importance = importance;
    }


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

    public int getLengthInSec() {
        return lengthInSec;
    }

    public void setLengthInSec(int lengthInSec) {
        this.lengthInSec = lengthInSec;
    }

    public short getImportance() {
        return importance;
    }

    public void setImportance(short importance) {
        this.importance = importance;
    }
}
