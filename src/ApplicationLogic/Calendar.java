package ApplicationLogic;

import java.util.*;
import java.util.stream.Stream;
import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.io.*;

public class Calendar {
    private final String AUTHORS = "Michał Forystek i Szymon Szymiczek";
    private int amountOfClocks;
    private long calendarLength;
    private boolean notifications;
    private List<Day> days = new ArrayList<>();
    private Map<String, Activity> activities = new HashMap<>();

    public Calendar() {

    }

//    public void addActivity(Activity newActivity, Date date, int occurringTime) {
//        activities.put(newActivity.getName(), newActivity);
//        int i = 0;
//        while (i <= days.size()){
//            if (days.get(i).getDate().equals(date)) {
//
//                days.get(i).addActivity(newActivity);
//                return;
//            }
//            i++;
//        }
//        System.out.println("incorrect date");
//    }

    public void delActivity(String activityName) {
//        activities.remove(activityName);
    }

    public void buyActivity() {

    }

    public void addDay() {

    }

    public void setCalendarLength() {

    }

    public void getActivity() {

    }
}