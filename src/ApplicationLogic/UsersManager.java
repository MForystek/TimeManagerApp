package ApplicationLogic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.CREATE;

public class UsersManager {
    private Scanner scanner = new Scanner(System.in);

    public User readConfiguration(String username, String password){
        List<String> config = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(username + password + ".txt"))) {
            stream.forEach(config::add);
            //reading basic user configuration
            var user = UserFactory.createUser(config.get(1), config.get(2), config.get(3), config.get(4), config.get(5));
            int i = 7;

            //reading Activities in Shop
            while ((i < config.size()) && (!config.get(i).equals("ActivitiesInCalendar"))) {
                if (config.get(i).equals("OneTimeActivity")) {
                    var oneTimeActivity = ActivityFactory.makeOneTimeActivity(
                            config.get(i + 1),
                            config.get(i + 2),
                            config.get(i + 3),
                            config.get(i + 4),
                            config.get(i + 5),
                            config.get(i + 6),
                            LocalDate.parse(config.get(i + 7))
                    );
                    user.getActivitiesInShop().put(oneTimeActivity.getName(), oneTimeActivity);
                    i += 8;
                } else if (config.get(i).equals("ProjectActivity")) {
                    var projectActivity = ActivityFactory.makeProjectActivity(
                            config.get(i + 1),
                            config.get(i + 2),
                            config.get(i + 3),
                            config.get(i + 4),
                            config.get(i + 5),
                            config.get(i + 6),
                            config.get(i + 7),
                            LocalDate.parse(config.get(i + 8))
                    );
                    user.getActivitiesInShop().put(projectActivity.getName(), projectActivity);
                    i += 9;
                } else if (config.get(i).equals("PeriodicActivity")) {
                    var periodicActivity = ActivityFactory.makePeriodicActivity(
                            config.get(i + 1),
                            config.get(i + 2),
                            config.get(i + 3),
                            config.get(i + 4),
                            config.get(i + 5),
                            config.get(i + 6)
                    );
                    user.getActivitiesInShop().put(periodicActivity.getName(), periodicActivity);
                    i += 7;
                } else {
                    i++;
                }
            }

            //reading Activities in Calendar
            while ((i < config.size()) && (!config.get(i).equals("Days"))) {
                if (config.get(i).equals("OneTimeActivity")) {
                    var oneTimeActivity = ActivityFactory.makeOneTimeActivity(
                            config.get(i + 1),
                            config.get(i + 2),
                            config.get(i + 3),
                            config.get(i + 4),
                            config.get(i + 5),
                            config.get(i + 6),
                            LocalDate.parse(config.get(i + 7))
                    );
                    user.getActivitiesInCalendar().put(oneTimeActivity.getName(), oneTimeActivity);
                    i += 8;
                } else if (config.get(i).equals("ProjectActivity")) {
                    var projectActivity = ActivityFactory.makeProjectActivity(
                            config.get(i + 1),
                            config.get(i + 2),
                            config.get(i + 3),
                            config.get(i + 4),
                            config.get(i + 5),
                            config.get(i + 6),
                            config.get(i + 7),
                            LocalDate.parse(config.get(i + 8))
                    );
                    user.getActivitiesInCalendar().put(projectActivity.getName(), projectActivity);
                    i += 9;
                } else if (config.get(i).equals("PeriodicActivity")) {
                    var periodicActivity = ActivityFactory.makePeriodicActivity(
                            config.get(i + 1),
                            config.get(i + 2),
                            config.get(i + 3),
                            config.get(i + 4),
                            config.get(i + 5),
                            config.get(i + 6)
                    );
                    user.getActivitiesInCalendar().put(periodicActivity.getName(), periodicActivity);
                    i += 7;
                } else {
                    i++;
                }
            }

            //reading Days
            while (i < config.size()) {
                if (config.get(i).equals("Day")) {
                    var day = new Day(LocalDate.parse(config.get(i + 1)));

//                    //reading Activities in Day
//                    while((i < config.size()) && (!config.get(i).equals("SegmentsInDay"))) {
//                        if (config.get(i).equals("OneTimeActivity")) {
//                            var oneTimeActivity = ActivityFactory.makeOneTimeActivity(
//                                    config.get(i + 1),
//                                    config.get(i + 2),
//                                    config.get(i + 3),
//                                    config.get(i + 4),
//                                    config.get(i + 5),
//                                    config.get(i + 6),
//                                    LocalDate.parse(config.get(i + 7))
//                            );
//                            day.getActivities().put(oneTimeActivity.getName(), oneTimeActivity);
//                            i += 8;
//                        } else if (config.get(i).equals("ProjectActivity")) {
//                            var projectActivity = ActivityFactory.makeProjectActivity(
//                                    config.get(i + 1),
//                                    config.get(i + 2),
//                                    config.get(i + 3),
//                                    config.get(i + 4),
//                                    config.get(i + 5),
//                                    config.get(i + 6),
//                                    config.get(i + 7),
//                                    LocalDate.parse(config.get(i + 8))
//                            );
//                            day.getActivities().put(projectActivity.getName(), projectActivity);
//                            i += 9;
//                        } else if (config.get(i).equals("PeriodicActivity")) {
//                            var periodicActivity = ActivityFactory.makePeriodicActivity(
//                                    config.get(i + 1),
//                                    config.get(i + 2),
//                                    config.get(i + 3),
//                                    config.get(i + 4),
//                                    config.get(i + 5),
//                                    config.get(i + 6)
//                            );
//                            day.getActivities().put(periodicActivity.getName(), periodicActivity);
//                            i += 7;
//                        } else {
//                            i++;
//                        }
//                    }
                    i++;
                    //reading Segments in Day
                    while ((i < config.size()) && (!config.get(i).equals("Day"))) {
                        if (config.get(i).equals("Segment")) {
                            var segment = ActivitySegmentFactory.makeActivitySegment(
                                    config.get(i + 1),
                                    config.get(i + 2),
                                    config.get(i + 3),
                                    config.get(i + 4)
                            );
                            day.getSegments().add(segment);
                            i += 5;
                        } else {
                            i++;
                        }
                    }

                    user.getDays().add(day);
                } else {
                    i++;
                }
            }

            return user;

        } catch (IOException e) {
            System.out.println(e);
            return UserFactory.createUser("error", "error", "0", "1", "false");
        }

    }

    public void saveConfiguration(User user) {
        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(Paths.get(user.getUsername() + user.getPassword() + ".txt"), CREATE))
        ) {
            //writing basic user configuration
            out.write((user.getLanguage() + "\n").getBytes());
            out.write((user.getUsername() + "\n").getBytes());
            out.write((user.getPassword() + "\n").getBytes());
            out.write((user.getAmountOfClocks() + "\n").getBytes());
            out.write((user.getCalendarLength() + "\n").getBytes());
            out.write((user.isNotifications() + "\n").getBytes());

            //writing Activities in Shop
            out.write(("ActivitiesInShop\n").getBytes());
            for (Map.Entry<String, Activity> activityEntry : user.getActivitiesInShop().entrySet()) {
                var activity = activityEntry.getValue();
                _writeActivities(activity, out);
            }

            //writing Activities in Calendar
            out.write(("ActivitiesInCalendar\n").getBytes());
            for (Map.Entry<String, Activity> activityEntry : user.getActivitiesInCalendar().entrySet()) {
                var activity = activityEntry.getValue();
                _writeActivities(activity, out);
            }

            //writing Days
            out.write(("Days\n").getBytes());
            for (var day : user.getDays()) {
                out.write(("Day\n").getBytes());
                out.write((day.getDate() + "\n").getBytes());

                //writing Activities in Day
//                out.write(("ActivitiesInDay\n").getBytes());
//                for (Map.Entry<String, Activity> activityEntry : day.getActivities().entrySet()) {
//                    var activity = activityEntry.getValue();
//                    _writeActivities(activity, out);
//                }

                //writing Segments in Day
                out.write(("SegmentsInDay\n").getBytes());
                for (var segment: day.getSegments()) {
                    out.write(("Segment\n").getBytes());
                    out.write((segment.getParentName() + "\n").getBytes());
                    out.write((segment.getLengthInSec() + "\n").getBytes());
                    out.write((segment.getValueInClocks() + "\n").getBytes());
                    out.write((segment.getOccurrenceTime() + "\n").getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void _writeActivities(Activity activity, OutputStream out) throws IOException {
        if (activity instanceof OneTimeActivity) {
            out.write(("OneTimeActivity\n").getBytes());
            _writeCommonThingsInActivity(activity, out);
            out.write((((OneTimeActivity) activity).getDeadline() + "\n").getBytes());
        } else if (activity instanceof ProjectActivity) {
            out.write(("ProjectActivity\n").getBytes());
            _writeCommonThingsInActivity(activity, out);
            out.write((((ProjectActivity) activity).getTotalLengthInSec() + "\n").getBytes());
            out.write((((ProjectActivity) activity).getDeadline() + "\n").getBytes());
        } else if (activity instanceof PeriodicActivity) {
            out.write(("PeriodicActivity\n").getBytes());
            _writeCommonThingsInActivity(activity, out);
        } else {
            System.out.println("ERROR during configuration saving in activity: " + activity.getName());
        }
    }

    private void _writeCommonThingsInActivity(Activity activity, OutputStream out) throws IOException {
        out.write((activity.getName() + "\n").getBytes());
        out.write((activity.getDescription() + "\n").getBytes());
        out.write((activity.getValueInClocks() + "\n").getBytes());
        out.write((activity.getImportance() + "\n").getBytes());
        out.write((activity.getRepetitionLengthInSec() + "\n").getBytes());
        out.write((activity.isDuty() + "\n").getBytes());
    }
}