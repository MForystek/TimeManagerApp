package ApplicationLogic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.CREATE;

public class UsersManager {
    private Scanner scanner = new Scanner(System.in);

    public static User readConfiguration(String username, String password){
        List<String> config = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get("usersConfigs/" + username + password + ".txt"))) {
            stream.forEach(config::add);
            return UserFactory.createUser(config.get(1), config.get(2), config.get(3), config.get(4), config.get(5));
        } catch (IOException e) {
            System.out.println(e);
            return UserFactory.createUser("error","error","0","1", "false");
        }
    }

    public static void saveConfiguration(User user) {
        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(Paths.get("usersConfigs/" + user.getUsername() + user.getPassword() + ".txt"), CREATE))) {
            out.write((user.getLanguage() + "\n").getBytes());
            out.write((user.getUsername() + "\n").getBytes());
            out.write((user.getPassword() + "\n").getBytes());
            out.write((user.getAmountOfClocks() + "\n").getBytes());
            out.write((user.getCalendarLength() + "\n").getBytes());
            out.write((user.isNotifications() + "\n").getBytes());
            out.write(("ActivitiesInShop\n").getBytes());
            out.write((user.getActivitiesInShop().size() + "\n").getBytes());
            for (Map.Entry<String, Activity> activityEntry : user.getActivitiesInShop().entrySet()) {
                var activity = activityEntry.getValue();
                if (activity instanceof OneTimeActivity) {
                    out.write(("OneTimeActivity\n").getBytes());
                    _writeActivity(activity, out);
                    out.write((((OneTimeActivity) activity).getDeadline() + "\n").getBytes());
                } else if (activity instanceof ProjectActivity) {
                    out.write(("ProjectActivity\n").getBytes());
                    _writeActivity(activity, out);
                    out.write((((ProjectActivity) activity).getTotalLengthInSec() + "\n").getBytes());
                    out.write((((ProjectActivity) activity).getDeadline() + "\n").getBytes());
                } else if (activity instanceof PeriodicActivity) {
                    out.write(("PeriodicActivity\n").getBytes());
                    _writeActivity(activity, out);
                } else {
                    System.out.println("ERROR during configuration saving in shop activity: " + activityEntry.getKey());
                }
            }

            out.write(("ActivitiesInCalendar\n").getBytes());
            out.write((user.getActivitiesInCalendar().size() + "\n").getBytes());
            for (Map.Entry<String, Activity> activityEntry : user.getActivitiesInCalendar().entrySet()) {
                var activity = activityEntry.getValue();
                if (activity instanceof OneTimeActivity) {
                    out.write(("OneTimeActivity\n").getBytes());
                    _writeActivity(activity, out);
                    out.write((((OneTimeActivity) activity).getDeadline() + "\n").getBytes());
                } else if (activity instanceof ProjectActivity) {
                    out.write(("ProjectActivity\n").getBytes());
                    _writeActivity(activity, out);
                    out.write((((ProjectActivity) activity).getTotalLengthInSec() + "\n").getBytes());
                    out.write((((ProjectActivity) activity).getDeadline() + "\n").getBytes());
                } else if (activity instanceof PeriodicActivity) {
                    out.write(("PeriodicActivity\n").getBytes());
                    _writeActivity(activity, out);
                } else {
                    System.out.println("ERROR during configuration saving in calendar activity: " + activityEntry.getKey());
                }
            }

            out.write(("Days\n").getBytes());
            for (var day : user.getDays()) {
                out.write(("Day\n").getBytes());
                out.write((day.getDate() + "\n").getBytes());

                out.write(("ActivitiesInDay\n").getBytes());
                out.write((day.getActivities().size() + "\n").getBytes());
                for (Map.Entry<String, Activity> activityEntry : day.getActivities().entrySet()) {
                    var activity = activityEntry.getValue();
                    if (activity instanceof OneTimeActivity) {
                        out.write(("OneTimeActivity\n").getBytes());
                        _writeActivity(activity, out);
                        out.write((((OneTimeActivity) activity).getDeadline() + "\n").getBytes());
                    } else if (activity instanceof ProjectActivity) {
                        out.write(("ProjectActivity\n").getBytes());
                        _writeActivity(activity, out);
                        out.write((((ProjectActivity) activity).getTotalLengthInSec() + "\n").getBytes());
                        out.write((((ProjectActivity) activity).getDeadline() + "\n").getBytes());
                    } else if (activity instanceof PeriodicActivity) {
                        out.write(("PeriodicActivity\n").getBytes());
                        _writeActivity(activity, out);
                    } else {
                        System.out.println("ERROR during configuration saving in days activity: " + activityEntry.getKey());
                    }
                }

                out.write(("SegmentsInDay\n").getBytes());
                out.write((day.getSegments().size() + "\n").getBytes());
                for (var segment: day.getSegments()) {
                    out.write((segment.getParentName() + "\n").getBytes());
                    out.write((segment.getLengthInSec() + "\n").getBytes());
                    out.write((segment.getValueInClocks() + "\n").getBytes());
                    out.write((segment.getOccurrenceTime() + "\n").getBytes());
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void _writeActivity(Activity activity, OutputStream out) throws IOException {
        out.write((activity.getName() + "\n").getBytes());
        out.write((activity.getDescription() + "\n").getBytes());
        out.write((activity.getValueInClocks() + "\n").getBytes());
        out.write((activity.getImportance() + "\n").getBytes());
        out.write((activity.getRepetitionLengthInSec() + "\n").getBytes());
        out.write((activity.isDuty() + "\n").getBytes());
    }
}