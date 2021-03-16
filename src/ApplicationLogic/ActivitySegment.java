package ApplicationLogic;

public class ActivitySegment {

    private String parentName;
    private int durationInSec;
    private int valueInClocks;
    private int occurrenceTime;

    public static ActivitySegment makeActivitySegment(
            String parentName,
            String lengthInSec,
            String valueInClocks,
            String occurrenceTime
    ) {
        return new ActivitySegment(
                parentName,
                Integer.parseInt(lengthInSec),
                Integer.parseInt(valueInClocks),
                Integer.parseInt(occurrenceTime)
        );
    }

    public ActivitySegment(String parentName, int durationInSec, int valueInClocks, int occurrenceTime) {
        this.parentName = parentName;
        this.durationInSec = durationInSec;
        this.valueInClocks = valueInClocks;
        this.occurrenceTime = occurrenceTime;
    }

    //getters & setters
    public int getDurationInSec() {
        return durationInSec;
    }

    public void setDurationInSec(int durationInSec) {
        this.durationInSec = durationInSec;
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
        return occurrenceTime + durationInSec;
    }
}


