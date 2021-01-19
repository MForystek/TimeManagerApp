package ApplicationLogic;

public class ActivitySegment {

    private String parentName;
    private int lengthInSec;
    private int valueInClocks;
    private int occurrenceTime;

    public ActivitySegment(String parentName, int lengthInSec, int valueInClocks, int occurrenceTime) {
        this.parentName = parentName;
        this.lengthInSec = lengthInSec;
        this.valueInClocks = valueInClocks;
        this.occurrenceTime = occurrenceTime;
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

//    public void setValueInClocks(int valueInClocks) {
//        this.valueInClocks = valueInClocks;
//    }

    public int getOccurrenceTime() {
        return occurrenceTime;
    }

//    public void setOccurrenceTime(int occurrenceTime) {
//        this.occurrenceTime = occurrenceTime;
//    }

    public String getParentName() {
        return parentName;
    }

    public int getEndTime() {
        return occurrenceTime + lengthInSec;
    }
}


