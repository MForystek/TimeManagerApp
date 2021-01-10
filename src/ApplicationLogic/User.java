package ApplicationLogic;

import java.util.*;

public class User {
    private String language;
    private String username;
    private String password;
    private int amountOfClocks;
    private int calendarLength;
    private boolean notifications;
    private List<Day> days;
    private Map<String, Activity> activitiesInCalendar;
    private Map<String, Activity> activitiesInShop;

    public User(
            String language,
            String username,
            String password,
            int amountOfClocks,
            int calendarLength,
            boolean notifications
    ) {
        this.language = language;
        this.username = username;
        this.password = password;
        this.amountOfClocks = amountOfClocks;
        this.calendarLength = calendarLength;
        this.notifications = notifications;
        this.days = new ArrayList<>();
        this.activitiesInCalendar = new HashMap<>();
        this.activitiesInShop = new HashMap<>();
    }

    public User(
            String language,
            String username,
            String password,
            int amountOfClocks,
            int calendarLength,
            boolean notifications,
            List<Day> days, Map<String,
            Activity> activitiesInCalendar,
            Map<String, Activity> activitiesInShop
    ) {
        this.language = language;
        this.username = username;
        this.password = password;
        this.amountOfClocks = amountOfClocks;
        this.calendarLength = calendarLength;
        this.notifications = notifications;
        this.days = days;
        this.activitiesInCalendar = activitiesInCalendar;
        this.activitiesInShop = activitiesInShop;
    }

    public void addClocks(int clocks) {
        if (clocks >= 0) {
            this.amountOfClocks += clocks;
        }
    }

    public void removeClocks(int clocks) {
        if (clocks >= 0 && this.amountOfClocks + clocks >= 0) {
            this.amountOfClocks -= clocks;
        }
    }

    public void sortDays() {
        Collections.sort(days);
    }

    //getters & setters
    public String getLanguage() {
        return language;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAmountOfClocks() {
        return amountOfClocks;
    }

    public void setAmountOfClocks(int amountOfClocks) {
        this.amountOfClocks = amountOfClocks;
    }

    public int getCalendarLength() {
        return calendarLength;
    }

    public void setCalendarLength(short calendarLength) {
        this.calendarLength = calendarLength;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public Map<String, Activity> getActivitiesInCalendar() {
        return activitiesInCalendar;
    }

    public void setActivitiesInCalendar(Map<String, Activity> activitiesInCalendar) {
        this.activitiesInCalendar = activitiesInCalendar;
    }

    public Map<String, Activity> getActivitiesInShop() {
        return activitiesInShop;
    }

    public void setActivitiesInShop(Map<String, Activity> activitiesInShop) {
        this.activitiesInShop = activitiesInShop;
    }
}
