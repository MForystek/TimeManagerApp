package ApplicationLogic;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        var calendar = new Calendar();
        for (int i = 0; i <calendar.getCalendarLength(); i++) {
            LocalDate localDate = LocalDate.now().plusDays(i);
            calendar.addDay(localDate);
        }
        calendar.setAmountOfClocks(100);
        var activityOneTime = ActivityFactory.makeOneTimeActivity(
                "Wynieś dzieci",
                "za drzwi",
                "10",
                "10",
                "5",
                "false",
                LocalDate.of(2021, 1, 10)
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
                "180",
                "true"
        );
        calendar.addActivityToShop(activityOneTime);
        calendar.addActivityToShop(activityProject);
        calendar.addActivityToShop(activityPeriodic);
        calendar.buyActivity(activityOneTime);
        calendar.buyActivity(activityProject);
        calendar.buyActivity(activityPeriodic);
        calendar.putSegment(calendar.getDays().get(2), calendar.getActivitiesInCalendar().get("Wynieś dzieci").getNextSegment(50400));
        calendar.putSegment(calendar.getDays().get(2), calendar.getActivitiesInCalendar().get("Bądź prezydentem").getNextSegment(50500));
        calendar.putSegment(calendar.getDays().get(4), calendar.getActivitiesInCalendar().get("Zabij kolege").getNextSegment(360));

    }
}
