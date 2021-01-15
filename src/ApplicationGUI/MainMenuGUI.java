package ApplicationGUI;

import ApplicationLogic.Calendar;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainMenuGUI extends Application {
    private Calendar calendar;
    private boolean userLoggedIn;

    public MainMenuGUI() {
        calendar = new Calendar();
        userLoggedIn = false;
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        //STAGES DECLARATIONS
        Stage shopStage = new Stage();
        Stage calendarStage = new Stage();
        Stage settingsStage = new Stage();

        //SCENES DECLARATIONS
        Scene mainMenuScene;

//MAIN MENU

        //Time Manager Logo Label
        Label logoLabel = new Label("TIME MANAGER");
        logoLabel.setFont(Font.font(25));

        //Current Time Label
        Label timeLabel = new Label();
        timeLabel.setFont(Font.font(25));
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            timeLabel.setText("Time: " + currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        //Current Date Label
        Label dateLabel = new Label();
        dateLabel.setFont(Font.font(25));
        Timeline date = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDate currentDate = LocalDate.now();
            dateLabel.setText("Date: " + currentDate.format(DateTimeFormatter.ofPattern("dd-MM-uuuu")));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        date.setCycleCount(Animation.INDEFINITE);
        date.play();

        //User Label
        Label userLabel = new Label("Not logged in");
        userLabel.setFont(Font.font(15));

        //CURRENT ACTIVITY

        //Informational Label
        Label activityLabel = new Label("Current Activity: ");
        activityLabel.setFont(Font.font(15));

        //Name Label
        Label activityName = new Label();
        activityName.setText("Name: " + "name_goes_here");

        // Description Label
        Label activityDescription = new Label();
        activityDescription.setText("Description: " + "description_goes_here");

        //ClocksValue Label
        Label activityValue = new Label();
        activityValue.setText("Value: " + "value_goes_here" + " clocks");

        //Importance Label
        Label activityImportance = new Label();
        activityImportance.setText("Importance: " + "importance_goes_here");

        //Length Label
        Label activityLength = new Label();
        activityLength.setText("Length: " + "Length_goes_here");

        //TimeTillEnd Label
        Label activityTimeTillEnd = new Label();
        activityTimeTillEnd.setText("Time till end: " + "TimeTillEnd_goes_here");

        //Current Activity Node
        VBox currentActivity = new VBox();
        currentActivity.setAlignment(Pos.TOP_LEFT);
        currentActivity.setSpacing(2);
        currentActivity.getChildren().addAll(
                activityLabel, activityName, activityDescription, activityValue,
                activityImportance, activityLength, activityTimeTillEnd
        );

        //NEXT ACTIVITY

        //Informational Label
        Label nextActivityLabel = new Label("Next Activity: ");
        nextActivityLabel.setFont(Font.font(15));

        //Name Label
        Label nextActivityName = new Label();
        nextActivityName.setText("Name: " + "name_goes_here");

        //Description Label
        Label nextActivityDescription = new Label();
        nextActivityDescription.setText("Description: " + "description_goes_here");

        //ClocksValue Label
        Label nextActivityValue = new Label();
        nextActivityValue.setText("Value: " + "value_goes_here" + " clocks");

        //Importance Label
        Label nextActivityImportance = new Label();
        nextActivityImportance.setText("Importance: " + "importance_goes_here");

        //Length Label
        Label nextActivityLength = new Label();
        nextActivityLength.setText("Length: " + "Length_goes_here");

        //TimeTillEnd Label
        Label nextActivityTimeTillEnd = new Label();
        nextActivityTimeTillEnd.setText("Time till end: " + "TimeTillEnd_goes_here");

        Group group = new Group(nextActivityLabel, nextActivityName, nextActivityDescription, nextActivityValue,
                nextActivityImportance, nextActivityLength, nextActivityTimeTillEnd);

        //Current Activity Node
        VBox nextActivity = new VBox();
        nextActivity.setAlignment(Pos.TOP_LEFT);
        nextActivity.setSpacing(2);
        nextActivity.getChildren().addAll(
                nextActivityLabel, nextActivityName, nextActivityDescription, nextActivityValue,
                nextActivityImportance, nextActivityLength, nextActivityTimeTillEnd
        );

        //Signing fields
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        TextField usernameTextField = new TextField();
        PasswordField passwordField = new PasswordField();

        //Informational Label
        Label informationalLabel = new Label();
        informationalLabel.setAlignment(Pos.CENTER);

        //MENU BUTTONS

        //Shop Button
        Button shopButton = new Button("SHOP");
        shopButton.setMinSize(120,70);
        shopButton.setOnAction((event -> {
            if (userLoggedIn) {
                try {
                    new ShopGUI(calendar).start(shopStage);
                    //stage.getScene().getRoot().getChildrenUnmodifiable().get(1).getText();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                informationalLabel.setText("You must be logged in to go to the Shop");
            }
        }));

        //Calendar Button
        Button calendarButton = new Button("CALENDAR");
        calendarButton.setMinSize(120,70);
        calendarButton.setOnAction((event -> {
            if (userLoggedIn) {
                try {
                    new CalendarGUI().start(calendarStage);
                    //stage.getScene().getRoot().getChildrenUnmodifiable().get(1).getText();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                informationalLabel.setText("You must be logged in to go to the Calendar");
            }
        }));

        //Settings Button
        Button settingsButton = new Button("SETTINGS");
        settingsButton.setMinSize(120,70);
        settingsButton.setOnAction((event -> {
            if (userLoggedIn) {
                try {
                    new SettingsGUI().start(settingsStage);
                    //stage.getScene().getRoot().getChildrenUnmodifiable().get(1).getText();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                    informationalLabel.setText("You must be logged in to go to the Settings");
                }
        }));

        //SignIn Button
        Button signInButton = new Button("Sign In");
        signInButton.setMinSize(80, 40);
        signInButton.setOnAction(event -> {
            if (calendar.signIn(usernameTextField.getText(), passwordField.getText())) {
                informationalLabel.setText("User " + usernameTextField.getText() + " logged successfully");
                userLoggedIn = true;
                userLabel.setText("Welcome " + usernameTextField.getText());
                usernameTextField.setText("");
                passwordField.setText("");
            } else {
                informationalLabel.setText("User with this username or password doesn't exist");
            }
        });

        //SignUp Button
        Button signUpButton = new Button("Sign Up");
        signUpButton.setMinSize(80, 40);
        signUpButton.setOnAction(event -> {
            if (usernameTextField.getText().equals("") || passwordField.getText().equals("")) {
                informationalLabel.setText("Username or password empty");
            } else {
                if (calendar.signUp(usernameTextField.getText(), passwordField.getText())
                ) {
                    informationalLabel.setText("User " + usernameTextField.getText() + " registered successfully");
                    userLoggedIn = true;
                    userLabel.setText("Welcome " + usernameTextField.getText());
                    usernameTextField.setText("");
                    passwordField.setText("");
                } else {
                    informationalLabel.setText("User with this username or password already exist");
                }
            }
        });

        //Logo, date and Time TilePane
        TilePane logoDateAndTimeTilePane = new TilePane(logoLabel, timeLabel, dateLabel);
        logoDateAndTimeTilePane.setAlignment(Pos.TOP_CENTER);
        logoDateAndTimeTilePane.setOrientation(Orientation.HORIZONTAL);
        logoDateAndTimeTilePane.setPrefColumns(3);
        logoDateAndTimeTilePane.setPrefRows(1);
        logoDateAndTimeTilePane.setTileAlignment(Pos.CENTER);
        logoDateAndTimeTilePane.setHgap(5);
        logoDateAndTimeTilePane.setVgap(5);

        //Activities TilePane
        TilePane mainMenuActivitiesTilePane = new TilePane(currentActivity, nextActivity);
        mainMenuActivitiesTilePane.setAlignment(Pos.TOP_CENTER);
        mainMenuActivitiesTilePane.setOrientation(Orientation.HORIZONTAL);
        mainMenuActivitiesTilePane.setPrefColumns(3);
        mainMenuActivitiesTilePane.setPrefRows(1);
        mainMenuActivitiesTilePane.setTileAlignment(Pos.CENTER);
        mainMenuActivitiesTilePane.setHgap(10);
        mainMenuActivitiesTilePane.setVgap(10);

        //Buttons TilePane
        TilePane mainMenuButtonsTilePane = new TilePane(shopButton, calendarButton, settingsButton);
        mainMenuButtonsTilePane.setAlignment(Pos.TOP_CENTER);
        mainMenuButtonsTilePane.setOrientation(Orientation.HORIZONTAL);
        mainMenuButtonsTilePane.setPrefColumns(3);
        mainMenuButtonsTilePane.setPrefRows(1);
        mainMenuButtonsTilePane.setTileAlignment(Pos.CENTER);
        mainMenuButtonsTilePane.setHgap(50);
        mainMenuButtonsTilePane.setVgap(10);


        //Signing GridPane
        GridPane mainMenuSigningGridPane = new GridPane();
        mainMenuSigningGridPane.addRow(0, usernameLabel, usernameTextField);
        mainMenuSigningGridPane.addRow(1, passwordLabel, passwordField);
        mainMenuSigningGridPane.addRow(2, signInButton, signUpButton);
        mainMenuSigningGridPane.setAlignment(Pos.TOP_CENTER);
        mainMenuSigningGridPane.setHgap(20);
        mainMenuSigningGridPane.setVgap(10);

        //MainMenu Root Node
        VBox mainMenuRoot = new VBox(logoDateAndTimeTilePane, userLabel, mainMenuActivitiesTilePane, mainMenuButtonsTilePane, informationalLabel, mainMenuSigningGridPane);
        mainMenuRoot.setSpacing(5);
        mainMenuRoot.setAlignment(Pos.TOP_CENTER);
        mainMenuRoot.setPadding(new Insets(10, 20, 10, 20));

        //MainMenu Scene
        mainMenuScene = new Scene(mainMenuRoot);

        //MainMenu Stage
        //mainStage.setWidth(650);
        //mainStage.setHeight(400);
        mainStage.setTitle("Time Manager");
        mainStage.setScene(mainMenuScene);
        mainStage.show();
    }

    public static void main(String[] args) {
        MainMenuGUI.launch(args);
    }
}
