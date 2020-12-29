package ApplicationLogic;

public class ActivitySegment {

    private int lengthInSec;
    private int valueInClocks;

    public ActivitySegment(int lengthInSec, int valueInClocks) {
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
}


