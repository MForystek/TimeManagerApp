package ApplicationGUI;

import ApplicationLogic.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivityListInCalendarGUI extends Application implements IObserver, IObservable {
    private Calendar calendar;
    private GridPane activitiesInCalendarGridPane;
    List<Stage> detailsStages = new ArrayList<>();
    List<Stage> scheduleStages = new ArrayList<>();
    private IObserver iObserver;

    public ActivityListInCalendarGUI(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void start(Stage aLICStage) throws Exception {
        aLICStage.setOnCloseRequest(event -> {
            if (detailsStages.size() > 0) {
                for (var detailStage : detailsStages) {
                    detailStage.close();
                }
            }
            if (scheduleStages.size() > 0) {
                for (var scheduleStage : scheduleStages) {
                    scheduleStage.close();
                }
            }
        });

        Label activityListLabel = new Label("List of activities in Calendar");
        activityListLabel.setFont(Font.font(15));

        activitiesInCalendarGridPane = _makeActivitiesGroup(activitiesInCalendarGridPane);
        activitiesInCalendarGridPane.setAlignment(Pos.TOP_CENTER);
        activitiesInCalendarGridPane.setHgap(5);
        activitiesInCalendarGridPane.setVgap(10);

        //Root VBox
        VBox rootVBox = new VBox(activityListLabel, activitiesInCalendarGridPane);
        rootVBox.setSpacing(10);
        rootVBox.setPadding(new Insets(10,20,10,20));
        rootVBox.setAlignment(Pos.TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(rootVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene scene = new Scene(scrollPane);

        aLICStage.setTitle("Calendar list of Activity");
        aLICStage.setScene(scene);
        aLICStage.setMinWidth(600);
        aLICStage.show();
    }

    @Override
    public void update() {
        activitiesInCalendarGridPane = _makeActivitiesGroup(activitiesInCalendarGridPane);
        notifyObserver();
    }

    @Override
    public void addObserver(IObserver iObserver) {
        this.iObserver = iObserver;
    }

    @Override
    public void notifyObserver() {
        iObserver.update();
    }

    private GridPane _makeActivitiesGroup(GridPane activitiesInCalendarGridPane) {
        ActivityListInCalendarGUI dys = this;
        if (activitiesInCalendarGridPane == null) {
            activitiesInCalendarGridPane = new GridPane();
        }
        activitiesInCalendarGridPane.getChildren().clear();
        for (Map.Entry<String, Activity> activitySet : calendar.getUser().getActivitiesInCalendar().entrySet()) {
            var activity = activitySet.getValue();
            Label activityName = new Label(activitySet.getKey());
            activityName.setWrapText(true);
            activityName.setMaxWidth(200);
            Label isDuty = new Label(activity.isDuty() ? "Duty" : "Pleasure");
            Label activityValue = new Label(activity.isDuty() ? "+" + activity.getValueInClocks() + " clocks" : "-" + activity.getValueInClocks() + " clocks");

            //Details Stage
            Stage detailsStage = new Stage();
            detailsStages.add(detailsStage);

            //Activity details Button
            Button detailsButton = new Button("Details");
            detailsButton.setMinSize(70,30);
            detailsButton.setOnAction(event -> {
                try {
                    new ActivityDetailsGUI(activity).start(detailsStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            //needed in lower code
            GridPane finalActivitiesInShopGridPane = activitiesInCalendarGridPane;

            //Schedule Stage
            Stage scheduleStage = new Stage();
            scheduleStages.add(scheduleStage);

            //Schedule activity Button
            Button scheduleButton = new Button("Schedule");
            scheduleButton.setMinSize(70, 30);
            //if amount of segments greater than allowed make it not clickable

            //Sell activity Button
            Button sellButton = new Button("Sell");
            sellButton.setMinSize(70, 30);

            //Schedule Button event
            scheduleButton.setOnAction(event -> {
                try {
                    var temp = new ActivityScheduleGUI(calendar, activity);
                    temp.addObserver(dys);
                    temp.start(scheduleStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            //Sell Button event
            sellButton.setOnAction(event -> {
                calendar.sellActivity(activity);
                calendar.addActivityToShop(activity);
                finalActivitiesInShopGridPane.getChildren().removeAll(activityName, isDuty, activityValue, detailsButton, scheduleButton, sellButton);
            });

            activitiesInCalendarGridPane.addRow(activitiesInCalendarGridPane.getRowCount(), activityName, isDuty, activityValue, detailsButton, scheduleButton, sellButton);
        }
        return activitiesInCalendarGridPane;
    }
}
