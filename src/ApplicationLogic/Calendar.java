package ApplicationLogic;

import java.util.*;

public class Calendar {
    private int amountOfClocks;
    private long calendarLength;
    private boolean notifications;
    private List<Day> days = new ArrayList<>();
    private Map<String, Activity> activitiesInCalendar;
    private Map<String, Activity> activitiesInShop;

    public Calendar() {
        activitiesInCalendar = new HashMap<>();
        activitiesInShop = new HashMap<>();
    }

    public int addActivityToShop(Activity activity) {
        if (activitiesInShop.containsKey(activity.getName())) {
            System.out.println("There already is an activity with this name in Shop");
            return 1;
        } else {
            activitiesInShop.put(activity.getName(), activity);
            System.out.println("Activity successfully added to the Shop");
            return 0;
        }
    }

    public int delActivityFromShop(String activityName) {
        if (!activitiesInShop.containsKey(activityName)) {
            System.out.println("There already is no activity with this name in Shop");
            return 1;
        } else {
            activitiesInShop.remove(activityName);
            System.out.println("Activity successfully removed from the Shop");
            return 0;
        }
    }

    public int buyActivity(Activity activity) {
        if (!activitiesInShop.containsKey(activity.getName())) {
            System.out.println("There is no activity in Shop with this name");
            return 1;
        } else if (amountOfClocks < activity.getValueInClocks()) {
            System.out.println("You don't have enough clocks for it!");
            return 1;
        } else if (!activity.isDuty()) {
            amountOfClocks -= activity.getValueInClocks();
            activitiesInCalendar.put(activity.getName(), activity);
            System.out.println("Pleasure bought successfully");
            return 0;
        } else {
            activitiesInCalendar.put(activity.getName(), activity);
            System.out.println("Duty bought successfully");
            return 0;
        }
    }

    public int sellActivity(Activity activity) {
        if (!activitiesInCalendar.containsKey(activity.getName())) {
            System.out.println("There is no bought activity with this name");
            return 1;
        } else if (!activity.isDuty()) {
            amountOfClocks += activity.getValueInClocks();
            activitiesInCalendar.remove(activity.getName());
            System.out.println("Pleasure sold successfully");
        } else {
            activitiesInCalendar.remove(activity.getName());
            System.out.println("Duty sold successfully");
        }
        return 0;
    }

    public int putSegment(Day day, ActivitySegment segment) {
        if(day.addSegment(segment)) {
            System.out.println("Activity placed successfully");
            return 0;
        } else {
            System.out.println("Putting an Activity failed");
            return 1;
        }
    }

    public int removeSegment(Day day, ActivitySegment segment) {
        if(day.delSegment(segment)) {
            System.out.println("Activity removed successfully");
            return 0;
        } else {
            System.out.println("Removing an Activity failed");
            return 1;
        }
    }

    public void addDay(Date date) {
        days.add(new Day());
    }
}