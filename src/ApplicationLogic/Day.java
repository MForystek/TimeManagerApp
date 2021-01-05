package ApplicationLogic;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Day {
    private LocalDate date;
    private Map<String, Activity> activities = new HashMap<>();
    private List<ActivitySegment> segments= new ArrayList<>();
    private final int SECONDS_IN_THE_DAY = 86_400;
    private final int BREAK_TIME = 0;

    public Day(LocalDate date) {
        this.date = date;
    }

//    public void addActivity(Activity activity) {
//        activities.put(activity.getName(), activity);
//        if (_isEnoughSecondsInTheDay()) {
//            _addActivitySegment(activity.getNextSegment());
//            System.out.println("yeah we'll do it");
//        } else {
//            activities.remove(activity.getName());
//            System.out.println("you have no more time!");
//        }
//    }

//    public void delActivity(String activityName) {
//        Predicate<ActivitySegment> isChildOfActivity = segment -> activityName.equals(segment.getParentName());
//        Consumer<ActivitySegment> removeSegmentFromTheList = segment -> segments.remove(segment);
//        segments.stream().filter(isChildOfActivity).forEach(removeSegmentFromTheList);
//        activities.remove(activityName);
//    }

    public void removeSegment(ActivitySegment activitySegment) {
        segments.remove(activitySegment);
    }

    public int putSegment(ActivitySegment activitySegment) {
        if (isSpaceFor(activitySegment.getOccurrenceTime(), activitySegment.getLengthInSec())){
            segments.add(activitySegment);
            _quicksortSegments(segments, 0, segments.size() - 1);
            return 0;
        }else{
            return 1;
        }
    }

    public Map<String, Integer> getUsage() {
        int dutyCount = 0;
        int pleasureCount = 0;
        for (ActivitySegment segment : segments) {
            if (activities.get(segment.getParentName()).isDuty()) {
                dutyCount += segment.getLengthInSec();
            } else {
                pleasureCount += segment.getLengthInSec();
            }
        }
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("duty", (dutyCount/SECONDS_IN_THE_DAY)*100);
        hashMap.put("pleasure", (pleasureCount/SECONDS_IN_THE_DAY)*100);
        hashMap.put("free", 100 - hashMap.get("duty") - hashMap.get("pleasure"));
        return hashMap;
    }

    public boolean isSpaceFor(int occurrenceTime, int lengthInSec) {
        for (int i = 0; i < segments.size(); i++) {
            if (segments.get(i).getOccurrenceTime() > occurrenceTime){
                return segments.get(i).getOccurrenceTime() - occurrenceTime >= lengthInSec + BREAK_TIME
                        && segments.get(i - 1).getOccurrenceTime() - occurrenceTime >= segments.get(i - 1).getLengthInSec();
            }else if (i == segments.size() - 1){
                return segments.get(i).getOccurrenceTime() - occurrenceTime >= segments.get(i).getLengthInSec();
            }
        }
        return false;
    }

    private void _quicksortSegments(List<ActivitySegment> list, int start, int end) {
        if (start < end) {
            int p = _partition(list, start, end);
            _quicksortSegments(list, start, p - 1);
            _quicksortSegments(list, p + 1, end);
        }
    }
    private int _partition(List<ActivitySegment> list, int start, int end) {
        ActivitySegment pivot = list.get(end);
        int i = start;
        for (int j = start; j < end; j++) {
            if (list.get(j).getOccurrenceTime() < pivot.getOccurrenceTime()) {
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

    //getters & setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}