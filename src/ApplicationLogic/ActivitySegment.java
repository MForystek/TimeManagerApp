package ApplicationLogic;

public class ActivitySegment {

    private String parentName;
    private int lengthInSec;
    private int valueInClocks;
    private int occurrenceTime = -1; // this is used only when occurs in a day

    public ActivitySegment(String parentName, int lengthInSec, int valueInClocks) {
        this.parentName = parentName;
        this.lengthInSec = lengthInSec;
        this.valueInClocks = valueInClocks;
    }

    //getters & setters
    public int getLengthInSec() {
        return lengthInSec;
    }

    public void setLengthInSec(int lengthInSec) {
        this.lengthInSec = lengthInSec;
    }

    public int getValueInClocks() {
        return valueInClocks;
    }

    public void setValueInClocks(int valueInClocks) {
        this.valueInClocks = valueInClocks;
    }

    public int getOccurrenceTime() {
        return occurrenceTime;
    }

    public void setOccurrenceTime(int occurrenceTime) {
        this.occurrenceTime = occurrenceTime;
    }

    public String getParentName() {
        return parentName;
    }
}


