package ApplicationLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.io.*;

public class Calendar {
    private final String AUTHORS = "Michał Forystek i Szymon Szymiczek";
    private String language;
    private String username;
    private String password;
    private int amountOfClocks;
    private long calendarLength;
    private boolean notifications;
    private List<Day> days;
    private List<Activity> activities;
    private Scanner scanner = new Scanner(System.in);

    public Calendar() {
        String temporaryUsername;
        String temporaryPassword;
        System.out.println("Input username:");
        temporaryUsername = scanner.next();
        System.out.println("Input password:");
        temporaryPassword = scanner.next();
        readConfiguration(temporaryUsername, temporaryPassword);
        saveConfiguration();
    }

    public void addActivity(byte activityType) {

    }

    public void delActivity(String activityName) {

    }

    public void buyActivity() {

    }

    public void addDay() {

    }

    public void setCalendarLength() {

    }



    public void readConfiguration(String theUsername, String thePassword) {
        try (Stream<String> stream = Files.lines(Paths.get("Configuration" + theUsername + thePassword + ".txt"))) {
            List<String> config = new ArrayList<>();
            stream.forEach(config::add);
            language = config.get(0);
            username = config.get(1);
            password = config.get(2);
            amountOfClocks = Integer.parseInt(config.get(3));
            calendarLength = Long.parseLong(config.get(4));
            notifications = Boolean.parseBoolean(config.get(5));
        } catch (IOException e) {
            language = "English";
            System.out.println("Hello new human being :D");
            System.out.println("Create your username: ");
            username = scanner.next();
            System.out.println("Hello " + username);
            System.out.println("Create your password: ");
            password = scanner.next();
            amountOfClocks = 0;
            calendarLength = 92;
            notifications = false;
            System.out.println("Welcome in our Time Manager!");
        }
        printConfiguration();
    }

    public void saveConfiguration() {
        byte[] languageBytes = (language + "\n").getBytes();
        byte[] usernameBytes = (username + "\n").getBytes();
        byte[] passwordBytes = (password + "\n").getBytes();
        byte[] amountOfClocksBytes = (((Integer)amountOfClocks) + "\n").getBytes();
        byte[] calendarLengthBytes = (((Long)calendarLength) + "\n").getBytes();
        byte[] notificationsBytes = (((Boolean)notifications) + "\n").getBytes();


        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(Paths.get("Configuration" + username + password + ".txt"), CREATE))) {
            out.write(languageBytes);
            out.write(usernameBytes);
            out.write(passwordBytes);
            out.write(amountOfClocksBytes);
            out.write(calendarLengthBytes);
            out.write(notificationsBytes);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void printConfiguration() {
        System.out.println(language);
        System.out.println(username);
        System.out.println(password);
        System.out.println(amountOfClocks);
        System.out.println(calendarLength);
        System.out.println(notifications);
    }

    public void getActivity() {

    }
}