package ApplicationLogic;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DayTest {

    @org.junit.Test
    public void isSpaceFor() {
        var calendar = new Calendar();
        for (int i = 0; i < 3; i++) {
            LocalDate localDate = LocalDate.now().plusDays(i);
            calendar.addDay(localDate);
        }
        var activityPeriodic = ActivityFactory.makePeriodicActivity(
                "Napisz projekt na PO",
                "Szybko",
                "50",
                "12",
                "100",
                "true"
        );
        calendar.addActivityToShop(activityPeriodic);
        calendar.buyActivity(activityPeriodic);

        //Day 0
        assertEquals(1, calendar.putSegment(calendar.getUser().getDays().get(0), calendar.getActivitiesInCalendar().get("Napisz projekt na PO").getNextSegment(-12)));     //przed 00:00
        assertEquals(0, calendar.putSegment(calendar.getUser().getDays().get(0), calendar.getActivitiesInCalendar().get("Napisz projekt na PO").getNextSegment(0)));       //o 00:00
        assertEquals(0, calendar.putSegment(calendar.getUser().getDays().get(0), calendar.getActivitiesInCalendar().get("Napisz projekt na PO").getNextSegment(86_300)));  //na styk przed 24:00
        assertEquals(1, calendar.putSegment(calendar.getUser().getDays().get(0), calendar.getActivitiesInCalendar().get("Napisz projekt na PO").getNextSegment(86_320)));  //na mniej niż styk przed 24:00
        assertEquals(1, calendar.putSegment(calendar.getUser().getDays().get(0), calendar.getActivitiesInCalendar().get("Napisz projekt na PO").getNextSegment(100_000)));  //po 24:00

        //Day 1
        assertEquals(0, calendar.putSegment(calendar.getUser().getDays().get(1), calendar.getActivitiesInCalendar().get("Napisz projekt na PO").getNextSegment(200)));
        assertEquals(1, calendar.putSegment(calendar.getUser().getDays().get(1), calendar.getActivitiesInCalendar().get("Napisz projekt na PO").getNextSegment(150)));     //za mało czasu po
        assertEquals(1, calendar.putSegment(calendar.getUser().getDays().get(1), calendar.getActivitiesInCalendar().get("Napisz projekt na PO").getNextSegment(250)));     //za mało czasu przed
        assertEquals(0, calendar.putSegment(calendar.getUser().getDays().get(1), calendar.getActivitiesInCalendar().get("Napisz projekt na PO").getNextSegment(500)));
        assertEquals(0, calendar.putSegment(calendar.getUser().getDays().get(1), calendar.getActivitiesInCalendar().get("Napisz projekt na PO").getNextSegment(350)));     //pomiędzy dwoma z luzem

        //Day 2
        assertEquals(0, calendar.putSegment(calendar.getUser().getDays().get(2), calendar.getActivitiesInCalendar().get("Napisz projekt na PO").getNextSegment(200)));
        assertEquals(0, calendar.putSegment(calendar.getUser().getDays().get(2), calendar.getActivitiesInCalendar().get("Napisz projekt na PO").getNextSegment(600)));
        assertEquals(0, calendar.putSegment(calendar.getUser().getDays().get(2), calendar.getActivitiesInCalendar().get("Napisz projekt na PO").getNextSegment(300)));     //pomiędzy, na styk po wcześniejszej
        assertEquals(0, calendar.putSegment(calendar.getUser().getDays().get(2), calendar.getActivitiesInCalendar().get("Napisz projekt na PO").getNextSegment(500)));     //pomiędzy, na styk przed późniejszą
        assertEquals(0, calendar.putSegment(calendar.getUser().getDays().get(2), calendar.getActivitiesInCalendar().get("Napisz projekt na PO").getNextSegment(400)));     //pomiędzy, na styk po wcześniejszej i przed późniejszą

    }
}