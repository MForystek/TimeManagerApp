package ApplicationLogic;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

public class Calendar {
    User user;

    public Calendar() {
        user = UserFactory.createUser("notNull", "notNull", "0", "1", "false");
    }

    public boolean signUp(String username, String password) {
        if (new File("usersConfigs/" + username + password + ".txt").isFile()) {
            return false;
        } else {
            user = UserFactory.createUser(username, password, "0", "92", "false");
            return true;
        }
    }

    public boolean signIn(String username, String password) {
        if (new File("usersConfigs/" + username + password + ".txt").isFile()) {
            user = UsersManager.readConfiguration(username, password);
            return true;
        } else {
            return false;
        }
    }

    public int addActivityToShop(Activity activity) {
        if (user.getActivitiesInShop().containsKey(activity.getName())) {
            System.out.println("There already is an activity with this name in Shop");
            return 1;
        } else {
            user.getActivitiesInShop().put(activity.getName(), activity);
            System.out.println("Activity successfully added to the Shop");
            return 0;
        }
    }

    public int delActivityFromShop(String activityName) {
        if (!user.getActivitiesInShop().containsKey(activityName)) {
            System.out.println("There already is no activity with this name in Shop");
            return 1;
        } else {
            user.getActivitiesInShop().remove(activityName);
            System.out.println("Activity successfully removed from the Shop");
            return 0;
        }
    }

    public int buyActivity(Activity activity) {
        if (!user.getActivitiesInShop().containsKey(activity.getName())) {
            System.out.println("There is no activity in Shop with this name");
            return 1;
        } else if(activity.isDuty()) {
            user.getActivitiesInCalendar().put(activity.getName(), activity);
            System.out.println("Duty bought successfully");
            return 0;
        } else if (user.getAmountOfClocks() < activity.getValueInClocks()) {
            System.out.println("You don't have enough clocks for it!");
            return 1;
        } else {
            user.decreaseAmountOfClocks(activity.getValueInClocks());
            user.getActivitiesInCalendar().put(activity.getName(), activity);
            System.out.println("Pleasure bought successfully");
            return 0;
        }
    }

    public int sellActivity(Activity activity) {
        if (!user.getActivitiesInCalendar().containsKey(activity.getName())) {
            System.out.println("There is no bought activity with this name");
            return 1;
        } else if (!activity.isDuty()) {
            user.increaseAmountOfClocks(activity.getValueInClocks());
            user.getActivitiesInCalendar().remove(activity.getName());
            System.out.println("Pleasure sold successfully");
        } else {
            user.getActivitiesInCalendar().remove(activity.getName());
            System.out.println("Duty sold successfully");
        }
        return 0;
    }

    public int putSegment(Day day, ActivitySegment segment) {
        if(day.putSegment(segment)) {
            System.out.println("Activity placed successfully");
            return 0;
        } else {
            System.out.println("Putting an Activity failed");
            return 1;
        }
    }

    public int removeSegment(Day day, ActivitySegment segment) {
        if(day.removeSegment(segment)) {
            System.out.println("Activity removed successfully");
            return 0;
        } else {
            System.out.println("Removing an Activity failed");
            return 1;
        }
    }

    // getters & setters
    public void addDay(LocalDate localDate) {
        user.getDays().add(new Day(localDate));
    }

    public long getCalendarLength() {
        return user.getCalendarLength();
    }

    public int getAmountOfClocks() {
        return user.getAmountOfClocks();
    }

    public void setAmountOfClocks(int amountOfClocks) {
        this.user.setAmountOfClocks(amountOfClocks);
    }

    public List<Day> getDays() {
        return user.getDays();
    }

    public Map<String, Activity> getActivitiesInCalendar() {
        return user.getActivitiesInCalendar();
    }
}