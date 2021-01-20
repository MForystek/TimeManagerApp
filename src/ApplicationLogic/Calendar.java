package ApplicationLogic;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class Calendar implements IObserver, IActivityShopAddDel {
    private User user;
    private UsersManager usersManager = new UsersManager();

    public Calendar() {
        user = UserFactory.createUser("notNull", "notNull", "0", "1", "false");
        _updateDays();
        startToday();
    }

    @Override
    public void update(){
        if (getDayByDate(LocalDate.now()).getDoneSegment() != null) {
            ActivitySegment segment = getDayByDate(LocalDate.now()).getDoneSegment();
            if (user.getActivitiesInCalendar().get(segment.getParentName()) instanceof OneTimeActivity) {
                user.getActivitiesInCalendar().remove(segment.getParentName());
            } else if (user.getActivitiesInCalendar().get(segment.getParentName()) instanceof ProjectActivity) {
                //subtraction of total length of project
                ((ProjectActivity) user.getActivitiesInCalendar().get(segment.getParentName())).subtractLength(segment.getLengthInSec());
                if (((ProjectActivity) user.getActivitiesInCalendar().get(segment.getParentName())).getTotalLengthInSec() <= 0) {
                    user.getActivitiesInCalendar().remove(segment.getParentName());
                }
            } //else if (user.getActivitiesInCalendar().get(segment.getParentName()) instanceof PeriodicActivity){}
            if (user.getActivitiesInCalendar().get(segment.getParentName()).isDuty())
                user.addClocks(segment.getValueInClocks());
            getDayByDate(LocalDate.now()).removeDoneSegment();
        }
        startToday();//condition is checked in method
    }

    public void startToday(){
        if (!getDayByDate(LocalDate.now()).isAlive()){
            getDayByDate(LocalDate.now()).start();
        }
    }

    public boolean signUp(String username, String password) {
        if (Files.exists(Paths.get("usersConfigs"))) {
            File theDir = new File("usersConfigs");
            theDir.mkdirs();
        }
        if (new File("usersConfigs/" + username + password + ".txt").isFile()) {
            return false;
        } else {
            for (var day : user.getDays()) {
                day.interrupt();
            }
            user = UserFactory.createUser(username, password, "0", "30", "false");
            for (int i = 0; i < user.getCalendarLength(); i++) {
                addDay(LocalDate.now().plusDays(i));
            }
            this._updateDays();
            startToday();
            return true;
        }
    }

    public boolean signIn(String username, String password) {
        if (Files.exists(Paths.get("usersConfigs"))) {
            if (new File("usersConfigs/" + username + password + ".txt").isFile()) {
                for (var day : user.getDays()) {
                    day.interrupt();
                }
                user = usersManager.readConfiguration(username, password);
                this._updateDays();
                for (var day : user.getDays()) {
                    day.addObserver(this);
                }
                startToday();
                return true;
            } else {
                return false;
            }
        } else {
            File theDir = new File("usersConfigs");
            theDir.mkdirs();
            return false;
        }
    }

    public void saveAccount(User user) {
        usersManager.saveConfiguration(user);
    }

    public void logout() {
        user = UserFactory.createUser("notNull", "notNull", "0", "1", "false");
    }

    public boolean delAccount(String username, String password) {
        var result = new File("usersConfigs/" + username + password + ".txt").delete();
        user = UserFactory.createUser("notNull", "notNull", "0", "1", "false");
        return result;
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
            return 2;
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

    public int ilośćWystąpieńSegmentu(Activity activity){
        int counter = 0;
        for (int i = 0; i < user.getDays().size(); i++) {
            for (int j = 0; j < user.getDays().get(i).getSegments().size(); j++) {
                if (user.getDays().get(i).getSegments().get(j).getParentName().equals(activity.getName())){
                    counter++;
                }
            }
        }
        return counter;
    } //testing needed

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
        day.addObserver(this);
        user.getDays().add(day);
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