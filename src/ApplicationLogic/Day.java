package ApplicationLogic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Day extends Thread implements Comparable<Day>, IObservable {
    private LocalDate date;
    //private Map<String, Activity> activities = new HashMap<>();
    private List<ActivitySegment> segments= new ArrayList<>();
    private final int SECONDS_IN_THE_DAY = 86_400;
    private final int BREAK_TIME = 0; //minimal break between activities
    private List<IObserver> observers = new ArrayList<>();

    private ActivitySegment doneSegment;

    public Day(LocalDate date) {
        this.date = date;
        this.doneSegment = null;
    }

    public void run(){
        while(true) {
            //now with precision to seconds
            LocalTime now = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond());
            if (segments.size() > 0) {
                if (now.equals(LocalTime.ofSecondOfDay(segments.get(0).getOccurrenceTime()).minusMinutes(15))) {
                    Notification.show(segments.get(0).getParentName() + " starts in 15 minutes", LocalTime.ofSecondOfDay(segments.get(0).getOccurrenceTime()).toString(), 1);
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (now.equals(LocalTime.ofSecondOfDay(segments.get(0).getOccurrenceTime()))) {
                    Notification.show(segments.get(0).getParentName() + " started", "Time Manager", 1);
                }
                if (LocalTime.now().isAfter(LocalTime.ofSecondOfDay(segments.get(0).getOccurrenceTime() + segments.get(0).getDurationInSec()))) {
                    this.doneSegment = segments.get(0);
                    Notification.show(doneSegment.getParentName() + " finished, good job!", "Time Manager", 1);
                    notifyObserver();
                }
            }
            if (!LocalDate.now().equals(date)) {
                notifyObserver();
                interrupt();
            }
            try {
                sleep(1000);
            } catch (InterruptedException ignored) {}
        }
    }

    public void print(){
        System.out.println("00:00:00 - " + LocalTime.ofSecondOfDay(segments.get(0).getOccurrenceTime()) + " - free time");
        for (int i = 0; i < segments.size() - 1; i++) {
            System.out.println(LocalTime.ofSecondOfDay(segments.get(i).getOccurrenceTime()) + " - " + LocalTime.ofSecondOfDay(segments.get(i).getOccurrenceTime() + segments.get(i).getDurationInSec()) + " - " + segments.get(i).getParentName());
            if (segments.get(i+1).getOccurrenceTime() - segments.get(i).getOccurrenceTime() + segments.get(i).getDurationInSec() >= 60){ // break prints only if is longer than 1min
                System.out.println(LocalTime.ofSecondOfDay(segments.get(i).getOccurrenceTime() + segments.get(i).getDurationInSec()) + " - " + LocalTime.ofSecondOfDay(segments.get(i+1).getOccurrenceTime()) + " - break");
            }
        }
        System.out.println(LocalTime.ofSecondOfDay(segments.get(segments.size() - 1).getOccurrenceTime()) + " - " + LocalTime.ofSecondOfDay(segments.get(segments.size() - 1).getOccurrenceTime() + segments.get(segments.size() - 1).getDurationInSec()) + " - " + segments.get(segments.size() - 1).getParentName());
        System.out.println(LocalTime.ofSecondOfDay(segments.get(segments.size() - 1).getOccurrenceTime() + segments.get(segments.size() - 1).getDurationInSec()) + " - 24:00:00 - free time");
}

    public void addObserver(IObserver iObserver){
        if (!observers.contains(iObserver)) {
            observers.add(iObserver);
        }
    }

    public void notifyObserver(){
        for (IObserver observer : observers) {
            observer.update();
        }
    }

    public boolean removeSegment(ActivitySegment activitySegment) {
        if (segments.contains(activitySegment)) {
            segments.remove(activitySegment);
            return true;
        } else {
            return false;
        }
    }

    public void removeDoneSegment(){
        segments.remove(doneSegment);
        doneSegment = null;
    }

    public boolean putSegment(ActivitySegment activitySegment) {
        if (_isSpaceFor(activitySegment.getOccurrenceTime(), activitySegment.getDurationInSec())){
            segments.add(activitySegment);
            _quicksortSegments(segments, 0, segments.size() - 1);
            return true;
        } else {
            return false;
        }
    }

//    public Map<String, Integer> getUsage() { // return percentage of each activity-type in day
//        int dutyCount = 0;        int pleasureCount = 0;
//        for (ActivitySegment segment : segments) {
//            if (activities.get(segment.getParentName()).isDuty()) {
//                dutyCount += segment.getLengthInSec();
//            } else {
//                pleasureCount += segment.getLengthInSec();
//            }
//        }
//        Map<String, Integer> hashMap = new HashMap<>();
//        hashMap.put("duty", (dutyCount/SECONDS_IN_THE_DAY)*100);
//        hashMap.put("pleasure", (pleasureCount/SECONDS_IN_THE_DAY)*100);
//        hashMap.put("free", 100 - hashMap.get("duty") - hashMap.get("pleasure"));
//        return hashMap;
//    }

    @Override
    public int compareTo(Day other) {
        return date.compareTo(other.getDate());
    }

    private boolean _isSpaceFor(int occurrenceTime, int lengthInSec) {
        if (occurrenceTime < 0 || lengthInSec <= 0 || occurrenceTime + lengthInSec > SECONDS_IN_THE_DAY
                || LocalTime.now().isAfter(LocalTime.ofSecondOfDay(occurrenceTime))
        ) {
            return false;
        } else {
            boolean isAnythingAfter = true;
            boolean isAnythingBefore = true;
            boolean isEnoughTimeAfter = false;
            boolean isEnoughTimeBefore = false;
            for (int i = 0; i < segments.size(); i++) {
                if (segments.get(i).getOccurrenceTime() - occurrenceTime >= 0) {
                    isAnythingAfter = false;
                    if (segments.get(i).getOccurrenceTime() - (occurrenceTime + lengthInSec + BREAK_TIME) >= 0) {
                        isEnoughTimeAfter = true;
                    }
                    break;
                }
            }
            for (int i = segments.size() - 1; i >= 0; i--) {
                if (occurrenceTime - segments.get(i).getOccurrenceTime() >= 0) {
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

//    public Map<String, Activity> getActivities() {
//        return activities;
//    }

    public List<ActivitySegment> getSegments() {
        return segments;
    }

    public ActivitySegment getDoneSegment() {
        return doneSegment;
    }

//    public void setDoneSegmentNull() {
//        this.doneSegment = null;
//    }
}