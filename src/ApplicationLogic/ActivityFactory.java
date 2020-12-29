package ApplicationLogic;

public class ActivityFactory {

    public Activity makePeriodicActivity(String name, String description, String totalLengthInSec, String valueInClocks, String importance) {
        return new PeriodicActivity(Integer.parseInt(valueInClocks), name, description, Integer.parseInt(totalLengthInSec), Short.parseShort(importance));
    }

    public Activity makeProjectActivity(String name, String description, String totalLengthInSec, String valueInClocks, String importance, String L) {
        return new PeriodicActivity(Integer.parseInt(valueInClocks), name, description, Integer.parseInt(totalLengthInSec), Short.parseShort(importance));
    }

    public Activity makeOneTimeActivity(String name, String description, String totalLengthInSec, String valueInClocks, String importance, String L) {
        return new PeriodicActivity(Integer.parseInt(valueInClocks), name, description, Integer.parseInt(totalLengthInSec), Short.parseShort(importance));
    }
}
