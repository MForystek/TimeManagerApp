package ApplicationLogic;

public class ActivitySegment {

    private String parentName;
    private int lengthInSec;
    private int valueInClocks;
    private int occurringTime = -1; // this is used only when occurs in a day

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

    public int getOccurringTime() {
        return occurringTime;
    }

    public void setOccurringTime(int occurringTime) {
        this.occurringTime = occurringTime;
    }
}


