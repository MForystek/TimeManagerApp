package ApplicationLogic;

import java.util.Map;

public class Shop {
    private Map<String, Activity> activities;

    public void addActivity(Activity newActivity) {
        activities.put(newActivity.getName(), newActivity);
    }

    public void delActivity(String activityName, Calendar calendar) {
        calendar.delActivityFromCalendar(activityName);
        activities.remove(activityName);
    }

    public void buyActivity(String activityName) {

    }
}
