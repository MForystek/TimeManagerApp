package ApplicationGUI;

import ApplicationLogic.Calendar;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainMenuGUI extends Application {
    private Calendar calendar = new Calendar();

    @Override
    public void start(Stage mainStage) throws Exception {

        //Time Manager Logo Label
        Label logoLabel = new Label("TIME MANAGER");
        logoLabel.setFont(Font.font(25));

        //Current Time Label
        Label timeLabel = new Label();
        timeLabel.setFont(Font.font(25));
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            timeLabel.setText("Time: " + currentTime.format(DateTimeFormatter.ofPattern("kk:mm:ss")));
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

        //Current Activity Node
        VBox nextActivity = new VBox();
        nextActivity.setAlignment(Pos.TOP_LEFT);
        nextActivity.setSpacing(2);
        nextActivity.getChildren().addAll(
                nextActivityLabel, nextActivityName, nextActivityDescription, nextActivityValue,
                nextActivityImportance, nextActivityLength, nextActivityTimeTillEnd
        );

        //SECOND NEXT ACTIVITY

        //Informational Label
        Label secNextActivityLabel = new Label("Second Next Activity: ");
        secNextActivityLabel.setFont(Font.font(15));

        //Name Label
        Label secNextActivityName = new Label();
        secNextActivityName.setText("Name: " + "name_goes_here");

        //Description Label
        Label secNextActivityDescription = new Label();
        secNextActivityDescription.setText("Description: " + "description_goes_here");

        //ClocksValue Label
        Label secNextActivityValue = new Label();
        secNextActivityValue.setText("Value: " + "value_goes_here" + " clocks");

        //Importance Label
        Label secNextActivityImportance = new Label();
        secNextActivityImportance.setText("Importance: " + "importance_goes_here");

        //Length Label
        Label secNextActivityLength = new Label();
        secNextActivityLength.setText("Length: " + "Length_goes_here");

        //TimeTillEnd Label
        Label secNextActivityTimeTillEnd = new Label();
        secNextActivityTimeTillEnd.setText("Time till end: " + "TimeTillEnd_goes_here");

        //Current Activity Node
        VBox secNextActivity = new VBox();
        secNextActivity.setAlignment(Pos.TOP_LEFT);
        secNextActivity.setSpacing(2);
        secNextActivity.getChildren().addAll(
                secNextActivityLabel, secNextActivityName, secNextActivityDescription, secNextActivityValue,
                secNextActivityImportance, secNextActivityLength, secNextActivityTimeTillEnd
        );

        //Menu buttons
        Button shopButton = new Button("SHOP");
        shopButton.setMinSize(150,93);
        Button calendarButton = new Button("CALENDAR");
        calendarButton.setMinSize(150,93);
        Button settingsButton = new Button("SETTINGS");
        settingsButton.setMinSize(150,93);

        //Date and Time Node
        HBox logoDateAndTimeNode = new HBox(logoLabel, timeLabel, dateLabel);
        logoDateAndTimeNode.setAlignment(Pos.CENTER);
        logoDateAndTimeNode.setSpacing(10);

        //Activities Display Node
        HBox displayedActivities = new HBox(currentActivity, nextActivity, secNextActivity);
        displayedActivities.setAlignment(Pos.CENTER);
        displayedActivities.setSpacing(10);

        //Menu Buttons Node
        HBox menuButtons = new HBox(shopButton, calendarButton, settingsButton);
        menuButtons.setAlignment(Pos.CENTER);
        menuButtons.setSpacing(60);

        //Root Node
        VBox mainVBox = new VBox();
        mainVBox.setSpacing(5);
        mainVBox.setAlignment(Pos.TOP_CENTER);
        mainVBox.setPadding(new Insets(10, 20, 10, 20));
        mainVBox.getChildren().addAll(logoDateAndTimeNode, displayedActivities, menuButtons);

        Scene sceneMainMenu = new Scene(mainVBox);

        mainStage.setWidth(650);
        mainStage.setHeight(400);
        mainStage.setTitle("Time Manager");
        mainStage.setScene(sceneMainMenu);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
