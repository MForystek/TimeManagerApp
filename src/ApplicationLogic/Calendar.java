package ApplicationLogic;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

public class Calendar implements IObserver, IActivityShopAddDel {
    private User user;

    public Calendar() {
        user = UserFactory.createUser("notNull", "notNull", "0", "30", "false");
    }

    @Override
    public void update(){
        ActivitySegment segment = getDayByDate(LocalDate.now()).getDoneSegment();
        if (user.getActivitiesInCalendar().get(segment.getParentName()) instanceof OneTimeActivity){
            user.getActivitiesInCalendar().remove(segment.getParentName());
        }else if (user.getActivitiesInCalendar().get(segment.getParentName()) instanceof ProjectActivity){
            ((ProjectActivity) user.getActivitiesInCalendar().get(segment.getParentName())).subtractLength(segment.getLengthInSec());
            if (((ProjectActivity) user.getActivitiesInCalendar().get(segment.getParentName())).getTotalLengthInSec() <= 0) {
                user.getActivitiesInCalendar().remove(segment.getParentName());
            }
        } //else if (user.getActivitiesInCalendar().get(segment.getParentName()) instanceof PeriodicActivity){}
        if (user.getActivitiesInCalendar().get(segment.getParentName()).isDuty())
            user.addClocks(segment.getValueInClocks());
        removeSegment(getDayByDate(LocalDate.now()), segment);
        getDayByDate(LocalDate.now()).setDoneSegment(null);
    }

    public boolean signUp(String username, String password) {
        if (new File("usersConfigs/" + username + password + ".txt").isFile()) {
            return false;
        } else {
            user = UserFactory.createUser(username, password, "0", "30", "false");
            for (int i = 0; i < user.getCalendarLength(); i++) {
                addDay(LocalDate.now().plusDays(i));
            }
            this._updateDays();
            return true;
        }
    }

    public boolean signIn(String username, String password) {
        if (new File("usersConfigs/" + username + password + ".txt").isFile()) {
            user = UsersManager.readConfiguration(username, password);
            this._updateDays();
            for (var day : user.getDays()) {
                day.setObserver(this);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean removeAccount(String username, String password) {
        var isDeleted = new File("usersConfigs/" + username + password + ".txt").delete();
        return isDeleted;
    }

    public int addActivityToShop(Activity activity) {
        if (user.getActivitiesInShop().containsKey(activity.getName())) {
            return 1;
        } else {
            user.getActivitiesInShop().put(activity.getName(), activity);
            return 0;
        }
    }

    @Override
    public int delActivityFromShop(String activityName) {
        if (!user.getActivitiesInShop().containsKey(activityName)) {

            return 1;
        } else {
            user.getActivitiesInShop().remove(activityName);
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
            user.removeClocks(activity.getValueInClocks());
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
            user.addClocks(activity.getValueInClocks());
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

    private void _updateDays() {
        user.getDays().removeIf(day -> day.getDate().isBefore(LocalDate.now()));
        int dayCounter = user.getDays().size();
        while (user.getCalendarLength() > user.getDays().size()) {
            addDay(LocalDate.now().plusDays(dayCounter));
            dayCounter++;
        }
    }

    // getters & setters
    public User getUser() {
        return user;
    }

    public void addDay(LocalDate localDate) {
        Day day = new Day(localDate);
        day.setObserver(this);
        user.getDays().add(day);
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

    public Day getDayByDate(LocalDate date) {
        for (var day : user.getDays()) {
            if (date.equals(day.getDate())) {
                return day;
            }
        }
        var newDay = new Day(date);
        user.getDays().add(newDay);
        return newDay;
    }

    public Map<String, Activity> getActivitiesInCalendar() {
        return user.getActivitiesInCalendar();
    }
}