package ApplicationLogic;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Calendar {
    private int calendarLength = 92;
    private List<Day> days;

    public void addDay() {
        //automatyczne dodawanie dni gdy poprzednie minęły
        days.add(new Day());
    }

    public void delActivityFromCalendar(String activityName) {
        days.stream().forEach(day -> day.unsetActivity(activityName));
    }
}