package ApplicationLogic;

import java.sql.Date;

public class ActivityFactory {

    public Activity makePeriodicActivity(String name, String description, String totalLengthInSec, String valueInClocks, String importance) {
        return new PeriodicActivity(name, description, Integer.parseInt(totalLengthInSec), Integer.parseInt(valueInClocks), Short.parseShort(importance));
    }

    public Activity makeProjectActivity(String name, String description, String totalLengthInSec, String valueInClocks, String importance, String lengthOfSegmentInSec, Date date) {
        return new ProjectActivity(name, description, Integer.parseInt(totalLengthInSec), Integer.parseInt(valueInClocks), Short.parseShort(importance),  Short.parseShort(lengthOfSegmentInSec), date);
    }

    public Activity makeOneTimeActivity(String name, String description, String totalLengthInSec, String valueInClocks, String importance, Date date) {
        return new OneTimeActivity(name, description, Integer.parseInt(totalLengthInSec), Integer.parseInt(valueInClocks), Short.parseShort(importance), date);
    }
}
