package ApplicationLogic;

public class UserFactory {
    public static User createUser(
            String username,
            String password,
            String amountOfClocks,
            String calendarLength,
            String notifications
    ) {
        return new User(
                "english",
                username,
                password,
                Integer.parseInt(amountOfClocks),
                Integer.parseInt(calendarLength),
                Boolean.parseBoolean(notifications)
        );
    }
}
