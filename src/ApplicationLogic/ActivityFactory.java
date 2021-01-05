package ApplicationLogic;

import java.time.LocalDate;

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
            LocalDate date
    ) {
        return new ProjectActivity(
                name,
                description,
                Integer.parseInt(valueInClocks),
                Short.parseShort(importance),
                Integer.parseInt(repetitionLengthInSec),
                Boolean.parseBoolean(isDuty),
                Integer.parseInt(totalLengthInSec),
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
            LocalDate date
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
