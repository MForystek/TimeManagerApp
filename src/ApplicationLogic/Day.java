package ApplicationLogic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day {
    private Date date;
    private List<Activity> activities;
    private List<ActivitySegment> segments;
    private final int SECONDS_IN_THE_DAY = 86_400;

    private int dayBeginning;
    private int dayEnding;

    public void addActivity(Activity activity) {
        activities.add(activity);
        if(_isEnoughSecondsInTheDay()){
            _addActivitySegment(activity.getNextSegment());
            System.out.println("yeah we'll do it");
        }else{
            activities.remove(activity);
            System.out.println("you have no more time!");
        }
    }

    public void delActivity(String activityName) {
        activities.remove(activityName);

        //need to resolve problem with existing segment which can differ form activityName.getNextSegment after some changes
    }


    public Map<String, Short> getUsage() {
        return new HashMap<String, Short>();
    }

    private void _addActivitySegment(ActivitySegment activitySegment) {

        //incomplete method

        _quicksortSegments(segments, 0, segments.size() -1);
    }
    private boolean _isEnoughSecondsInTheDay() {
        int sum_of_seconds = activities.stream().mapToInt(Activity::getRepetitionLengthInSec).sum();
        return sum_of_seconds <= SECONDS_IN_THE_DAY;
    }

    private void _quicksortSegments(List<ActivitySegment> list, int start, int end){
        if(start < end){
            int p = _partition(list, start, end);
            _quicksortSegments(list, start, p-1);
            _quicksortSegments(list, p+1, end);
        }
    }
    private int _partition(List<ActivitySegment> list, int start, int end){
        ActivitySegment pivot = list.get(end);
        int i = start;
        for (int j = start; j < end; j++) {
            if(list.get(j).getOccurringTime() < pivot.getOccurringTime()){
                ActivitySegment tmp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, tmp);
                i++;
            }
        }
        ActivitySegment tmp = list.get(i);
        list.set(i, list.get(end));
        list.set(end, tmp);
        return i;
    }

}
