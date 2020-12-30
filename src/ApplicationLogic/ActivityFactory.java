package ApplicationLogic;

import java.util.Date;

public class ActivityFactory {

    public static Activity makePeriodicActivity(
            String name,
            String description,
            String valueInClocks,
            String importance,
            String repetitionLengthInSec,
            String isDuty
    ) {
        return new PeriodicActivity(
                name,
                description,
                Integer.parseInt(valueInClocks),
                Short.parseShort(importance),
                Integer.parseInt(repetitionLengthInSec),
                Boolean.parseBoolean(isDuty)
        );
    }

    public static Activity makeProjectActivity(
            String name,
            String description,
            String valueInClocks,
            String importance,
            String repetitionLengthInSec,
            String isDuty,
            String totalLengthInSec,
            Date date
    ) {
        return new ProjectActivity(
                name,
                description,
                Integer.parseInt(valueInClocks),
                Short.parseShort(importance),
                Integer.parseInt(repetitionLengthInSec),
                Boolean.parseBoolean(isDuty),
                Short.parseShort(totalLengthInSec),
                date
        );
    }

    public static Activity makeOneTimeActivity(
            String name,
            String description,
            String valueInClocks,
            String importance,
            String repetitionLengthInSec,
            String isDuty,
            Date date
    ) {
        return new OneTimeActivity(
                name,
                description,
                Integer.parseInt(valueInClocks),
                Short.parseShort(importance),
                Integer.parseInt(repetitionLengthInSec),
                Boolean.parseBoolean(isDuty),
                date
        );
    }
}
