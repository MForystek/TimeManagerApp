package ApplicationLogic;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        var calendar = new Calendar();
        System.out.println(calendar.signIn("test", "test"));
        System.out.println(calendar.removeAccount("test", "test"));
        System.out.println(calendar.signUp("test", "test"));
        calendar.getUser().setAmountOfClocks(100);
        var activityOneTime = ActivityFactory.makeOneTimeActivity(
                "Wynieś dzieci",
                "za drzwi",
                "10",
                "10",
                "5",
                "false",
                LocalDate.of(2021, 1, 20)
        );
        var activityProject = ActivityFactory.makeProjectActivity(
                "Bądź prezydentem",
                "szybko",
                "1000",
                "8",
                "3600",
                "true",
                "36000",
                LocalDate.of(2021, 3, 1)
        );
        var activityPeriodic = ActivityFactory.makePeriodicActivity(
                "Zabij kolege",
                "nożem",
                "50",
                "12",
                "100",
                "true"
        );
        calendar.addActivityToShop(activityOneTime);
        calendar.addActivityToShop(activityProject);
        calendar.addActivityToShop(activityPeriodic);
        calendar.buyActivity(activityOneTime);
        calendar.buyActivity(activityProject);
        calendar.buyActivity(activityPeriodic);
        calendar.putSegment(calendar.getUser().getDays().get(0), calendar.getActivitiesInCalendar().get("Wynieś dzieci").getNextSegment(50400));
        calendar.putSegment(calendar.getUser().getDays().get(0), calendar.getActivitiesInCalendar().get("Bądź prezydentem").getNextSegment(50500));
        calendar.putSegment(calendar.getUser().getDays().get(1), calendar.getActivitiesInCalendar().get("Zabij kolege").getNextSegment(9_000));

        calendar.getUser().getDays().get(0).print();

        UsersManager.saveConfiguration(calendar.getUser());
    }
}
