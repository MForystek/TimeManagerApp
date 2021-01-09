package ApplicationLogic;

import java.time.LocalDate;
import java.util.*;

public class Day {
    private LocalDate date;
    private Map<String, Activity> activities = new HashMap<>();
    private List<ActivitySegment> segments= new ArrayList<>();
    private final int SECONDS_IN_THE_DAY = 86_400;
    private final int BREAK_TIME = 0; //minimal break between activities

    public Day(LocalDate date) {
        this.date = date;
    }

    public void print(){
        System.out.println("00:00:00 - " + secondsToTime(segments.get(0).getOccurrenceTime()) + " - free time");
        for (int i = 0; i < segments.size() - 1; i++) {
            System.out.println(secondsToTime(segments.get(i).getOccurrenceTime()) + " - " + secondsToTime(segments.get(i).getOccurrenceTime() + segments.get(i).getLengthInSec()) + " - " + segments.get(i).getParentName());
            if (segments.get(i+1).getOccurrenceTime() - segments.get(i).getOccurrenceTime() + segments.get(i).getLengthInSec() >= 60){ // break prints only if is longer than 1min
                System.out.println(secondsToTime(segments.get(i).getOccurrenceTime() + segments.get(i).getLengthInSec()) + " - " + secondsToTime(segments.get(i+1).getOccurrenceTime()) + " - break");
            }
        }
        System.out.println(secondsToTime(segments.get(segments.size() - 1).getOccurrenceTime()) + " - " + secondsToTime(segments.get(segments.size() - 1).getOccurrenceTime() + segments.get(segments.size() - 1).getLengthInSec()) + " - " + segments.get(segments.size() - 1).getParentName());
        System.out.println(secondsToTime(segments.get(segments.size() - 1).getOccurrenceTime() + segments.get(segments.size() - 1).getLengthInSec()) + " - 24:00:00 - free time");
}

    private String secondsToTime(int seconds){
        int hour = 0;
        while(seconds >= 3600){
            seconds -= 3600;
            hour++;
        }

        int minute = 0;
        while (seconds >= 60){
           seconds -= 60;
           minute++;
        }
        return hour + ":" + minute + ":" + seconds;
    }

    public boolean removeSegment(ActivitySegment activitySegment) {
        if (segments.contains(activitySegment)) {
            segments.remove(activitySegment);
            return true;
        } else {
            return false;
        }
    }

    public boolean putSegment(ActivitySegment activitySegment) {
        if (isSpaceFor(activitySegment.getOccurrenceTime(), activitySegment.getLengthInSec())){
            segments.add(activitySegment);
            _quicksortSegments(segments, 0, segments.size() - 1);
            return true;
        } else {
            return false;
        }
    }

    public Map<String, Integer> getUsage() { // return percentage of each activity-type in day
        int dutyCount = 0;        int pleasureCount = 0;
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
        if (occurrenceTime < 0 || lengthInSec <= 0 || occurrenceTime + lengthInSec > SECONDS_IN_THE_DAY) {
            return false;
        } else {
            boolean isAnythingAfter = true;
            boolean isAnythingBefore = true;
            boolean isEnoughTimeAfter = false;
            boolean isEnoughTimeBefore = false;
            for (int i = 0; i < segments.size(); i++) {
                if (segments.get(i).getOccurrenceTime() - occurrenceTime > 0) {
                    isAnythingAfter = false;
                    if (segments.get(i).getOccurrenceTime() - (occurrenceTime + lengthInSec + BREAK_TIME) >= 0) {
                        isEnoughTimeAfter = true;
                    }
                    break;
                }
            }
            for (int i = segments.size() - 1; i >= 0; i--) {
                if (occurrenceTime - segments.get(i).getOccurrenceTime() > 0) {
                    isAnythingBefore = false;
                    if (occurrenceTime - (segments.get(i).getEndTime() + BREAK_TIME) >= 0) {
                        isEnoughTimeBefore = true;
                    }
                    break;
                }
            }
            return (isAnythingBefore || isEnoughTimeBefore) && (isEnoughTimeAfter || isAnythingAfter);
        }
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

    public Map<String, Activity> getActivities() {
        return activities;
    }

    public List<ActivitySegment> getSegments() {
        return segments;
    }
}