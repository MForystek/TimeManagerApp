package ApplicationLogic;

import java.sql.Date;

public class ActivityFactory {

    public Activity makePeriodicActivity(String name, String description, String repetitionLengthInSec, String valueInClocks, String importance) {
        return new PeriodicActivity(name, description, Integer.parseInt(repetitionLengthInSec), Integer.parseInt(valueInClocks), Short.parseShort(importance));
    }

    public Activity makeProjectActivity(String name, String description, String repetitionLengthInSec, String valueInClocks, String importance, String totalLengthInSec, Date date) {
        return new ProjectActivity(name, description, Integer.parseInt(repetitionLengthInSec), Integer.parseInt(valueInClocks), Short.parseShort(importance),  Short.parseShort(totalLengthInSec), date);
    }

    public Activity makeOneTimeActivity(String name, String description, String repetitionLengthInSec, String valueInClocks, String importance, Date date) {
        return new OneTimeActivity(name, description, Integer.parseInt(repetitionLengthInSec), Integer.parseInt(valueInClocks), Short.parseShort(importance), date);
    }
}
