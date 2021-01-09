package ApplicationLogic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
        byte[] languageBytes = (user.getLanguage() + "\n").getBytes();
        byte[] usernameBytes = (user.getUsername() + "\n").getBytes();
        byte[] passwordBytes = (user.getPassword() + "\n").getBytes();
        byte[] amountOfClocksBytes = (user.getAmountOfClocks() + "\n").getBytes();
        byte[] calendarLengthBytes = (user.getCalendarLength() + "\n").getBytes();
        byte[] notificationsBytes = (user.isNotifications() + "\n").getBytes();

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(Paths.get("usersConfigs/" + user.getUsername() + user.getPassword() + ".txt"), CREATE))) {
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
}