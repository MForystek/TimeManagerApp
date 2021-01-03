package ApplicationLogic;

import java.util.Map;

public class Shop {
    private Map<String, Activity> activities;



    private Activity boughtActivity;

    public Shop(Map<String, Activity> activities){
        this.activities = activities;
    }

    public void addActivity(Activity newActivity) {
        activities.put(newActivity.getName(), newActivity);
    }

    public void delActivity(String activityName) {
        activities.remove(activityName);
    }

    public void buyActivity(String activityName) {
        boughtActivity = activities.get(activityName);
    }

    //getters & setters
    public Activity getBoughtActivity() {
        return boughtActivity;
    }

    public void setBoughtActivity(Activity boughtActivity) {
        this.boughtActivity = boughtActivity;
    }
}
