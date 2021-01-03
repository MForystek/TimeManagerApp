package ApplicationLogic;

import java.util.List;
import java.util.Map;

public class Calendar extends Thread {
    private int calendarLength = 92;
    private int amountOfClocks;
    private List<Day> days;
    private Map<String, Activity> activities;
    private Manager manager;
    private Shop shop;

    public void run(){
        if(shop.getBoughtActivity() != null){
            addActivity(shop.getBoughtActivity());
            reArrange();
            shop.setBoughtActivity(null);
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void reArrange(){
        //incomplete method
    }

    public void addActivity(Activity activity){
        activities.put(activity.getName(), activity);
    }

    public void addDay() {
        //automatically adding days after date change
        days.add(new Day());
    }

    public void delActivityFromCalendar(String activityName) {
        days.stream().forEach(day -> day.unsetActivity(activityName));
    }
}