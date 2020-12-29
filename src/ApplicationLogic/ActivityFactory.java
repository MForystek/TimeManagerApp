package ApplicationLogic;

public class ActivityFactory {

    public Activity makeActivity(String activityType, String name, String description, int totalLengthInSec, int valueInClocks, short importance) {
        if (activityType.equals("Periodic")){
            return new PeriodicActivity(valueInClocks, name, description, totalLengthInSec, importance, false, (short) 1);
        }
    }

}
