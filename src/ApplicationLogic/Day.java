package ApplicationLogic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day {
    private Date date;
    private List<Activity> activities;
    private final int SECONDS_IN_THE_DAY = 86_400;

    public void addActivity(Activity activity) {
        //addActivity here
    }

    public void addActivitySegment(ActivitySegment activitySegment) {

    }

    public void delActivity(String activityName) {
        activities.remove(activityName);
    }


    public Map<String, Short> getUsage() {
        return new HashMap<String, Short>();
    }

    public boolean isEnoughSecondsInTheDay() {
        int sum_of_seconds = activities.stream().mapToInt(Activity::getRepetitionLengthInSec).sum();
        return sum_of_seconds <= SECONDS_IN_THE_DAY;
    }
}
