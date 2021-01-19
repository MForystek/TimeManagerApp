package ApplicationGUI;

import ApplicationLogic.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CalendarGUI extends Application implements IObserver{
    private Calendar calendar;
    List<Stage> detailsStages = new ArrayList<>();
    GridPane activitiesInDayGridPane = new GridPane();
    DatePicker chooseDayDatePicker = new DatePicker(LocalDate.now());

    public CalendarGUI(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void start(Stage calendarStage) throws Exception {
        CalendarGUI dys = this;
        Stage aLICStage = new Stage();

        calendarStage.setOnCloseRequest(event -> {
            aLICStage.fireEvent(new WindowEvent(aLICStage, WindowEvent.WINDOW_CLOSE_REQUEST));
            aLICStage.close();
            if (detailsStages.size() > 0) {
                for (var detailStage : detailsStages) {
                    detailStage.close();
                }
            }
        });

        Label calendarLabel = new Label("CALENDAR");
        calendarLabel.setFont(Font.font(20));

        //Date and additional date info Labels
        Label dateLabel = new Label("Date: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-uuuu")));
        dateLabel.setFont(Font.font(15));
        Label dateAdditionalInfoLabel = new Label(
                LocalDate.now().getDayOfWeek().toString().charAt(0)
                        + LocalDate.now().getDayOfWeek().toString().substring(1).toLowerCase() + "-today"
        );
        dateAdditionalInfoLabel.setFont(Font.font(15));

        GridPane informationalLabelsAndDatePicker = new GridPane();
        informationalLabelsAndDatePicker.addRow(0, calendarLabel, chooseDayDatePicker);
        informationalLabelsAndDatePicker.addRow(1, dateLabel, dateAdditionalInfoLabel);
        informationalLabelsAndDatePicker.setHgap(10);
        informationalLabelsAndDatePicker.setVgap(5);
        informationalLabelsAndDatePicker.setAlignment(Pos.TOP_CENTER);

        _loadAndDisplayDay(activitiesInDayGridPane, chooseDayDatePicker);
        activitiesInDayGridPane.setAlignment(Pos.TOP_CENTER);
        activitiesInDayGridPane.setVgap(5);
        activitiesInDayGridPane.setHgap(10);
        activitiesInDayGridPane.setPadding(new Insets(10,10,10,10));

        //Choosing date event
        chooseDayDatePicker.setOnAction(event -> {
            if (chooseDayDatePicker.getValue().isBefore(LocalDate.now())
                    || chooseDayDatePicker.getValue().isAfter(LocalDate.now().plusDays(calendar.getUser().getCalendarLength()))
            ) {
                dateLabel.setText("Date: Wrong Date!");
                dateAdditionalInfoLabel.setText("");
                activitiesInDayGridPane.getChildren().clear();
            } else {
                dateLabel.setText("Date: " + chooseDayDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-uuuu")));
                if (chooseDayDatePicker.getValue().isEqual(LocalDate.now())) {
                    dateAdditionalInfoLabel.setText(
                            chooseDayDatePicker.getValue().getDayOfWeek().toString().charAt(0)
                                    + chooseDayDatePicker.getValue().getDayOfWeek().toString().substring(1).toLowerCase()
                                    + "-Today"
                    );
                } else if (chooseDayDatePicker.getValue().isEqual(LocalDate.now().plusDays(1))) {
                    dateAdditionalInfoLabel.setText(
                            chooseDayDatePicker.getValue().getDayOfWeek().toString().charAt(0)
                                    + chooseDayDatePicker.getValue().getDayOfWeek().toString().substring(1).toLowerCase()
                                    + "-Tomorrow"
                    );
                } else {
                    dateAdditionalInfoLabel.setText(
                            chooseDayDatePicker.getValue().getDayOfWeek().toString().charAt(0)
                                    + chooseDayDatePicker.getValue().getDayOfWeek().toString().substring(1).toLowerCase());
                }
                activitiesInDayGridPane.getChildren().clear();
                _loadAndDisplayDay(activitiesInDayGridPane, chooseDayDatePicker);
            }
        });

        //Activity List Button
        Button activityListButton = new Button("Activity List");
        activityListButton.setMinSize(70, 30);
        activityListButton.setOnAction(event -> {
            try {
                var temp = new ActivityListInCalendarGUI(calendar);
                temp.setObserver(dys);
                temp.start(aLICStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //Calendar Root
        VBox calendarRoot = new VBox(informationalLabelsAndDatePicker, activityListButton, activitiesInDayGridPane);
        calendarRoot.setAlignment(Pos.TOP_CENTER);
        calendarRoot.setPadding(new Insets(10,20,10,20));
        calendarRoot.setSpacing(10);

        ScrollPane scrollPane = new ScrollPane(calendarRoot);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene calendarScene = new Scene(scrollPane);

        calendarStage.setTitle("Calendar");
        calendarStage.setScene(calendarScene);
        calendarStage.setMinWidth(600);
        calendarStage.show();
    }

    @Override
    public void update() {
        _loadAndDisplayDay(activitiesInDayGridPane, chooseDayDatePicker);
    }

    private void _loadAndDisplayDay(GridPane activitiesInDayGridPane, DatePicker chooseDayDatePicker) {
        Day day = calendar.getDayByDate(chooseDayDatePicker.getValue());
        Consumer<ActivitySegment> addSegmentToGridPane = (segment -> {
            var parentActivity = calendar.getUser().getActivitiesInCalendar().get(segment.getParentName());
            var thisDate = chooseDayDatePicker.getValue();
            //Labels to display
            Label whenLabel = new Label(_makeFormToTimeString(
                    LocalTime.ofSecondOfDay(segment.getOccurrenceTime()), LocalTime.ofSecondOfDay(segment.getEndTime()))
            );
            Label activityName = new Label(parentActivity.getName());
            activityName.setWrapText(true);
            activityName.setMaxWidth(200);
            Label isDuty = new Label(parentActivity.isDuty() ? "Duty" : "Pleasure");
            Label segmentValue = new Label(parentActivity.isDuty()
                    ? "+" + segment.getValueInClocks() + " clocks" : "-" + segment.getValueInClocks() + " clocks"
            );

            Stage detailsStage = new Stage();
            detailsStages.add(detailsStage);

            //Activity details Button
            Button detailsButton = new Button("Details");
            detailsButton.setMinSize(70,30);
            detailsButton.setOnAction(event -> {
                try {
                    new ActivityDetailsGUI(parentActivity).start(detailsStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            //Activity unschedule Button
            Button unscheduleButton = new Button("Unschedule");
            unscheduleButton.setMinSize(70, 30);
            unscheduleButton.setOnAction(event -> {
                calendar.removeSegment(calendar.getDayByDate(thisDate), segment);
                activitiesInDayGridPane.getChildren().removeAll(whenLabel,activityName,isDuty,segmentValue,detailsButton,unscheduleButton);
            });

            activitiesInDayGridPane.addRow(activitiesInDayGridPane.getRowCount(),
                    whenLabel, activityName, isDuty, segmentValue, detailsButton, unscheduleButton
            );

        });
        activitiesInDayGridPane.getChildren().clear();
        day.getSegments().forEach(addSegmentToGridPane);
    }

    private String _makeFormToTimeString(LocalTime begining, LocalTime end) {
        String when = "";
        if (begining.getHour() < 10) {
            when += "0" + begining.getHour() + ":";
        } else {
            when += begining.getHour() + ":";
        }
        if (begining.getMinute() < 10) {
            when += "0" + begining.getMinute() + ":";
        } else {
            when += begining.getMinute() + ":";
        }
        if (begining.getSecond() < 10) {
            when += "0" + begining.getSecond() + " - ";
        } else {
            when += begining.getSecond() + " - ";
        }
        if (end.getHour() < 10) {
            when += "0" + end.getHour() + ":";
        } else {
            when += end.getHour() + ":";
        }
        if (end.getMinute() < 10) {
            when += "0" + end.getMinute() + ":";
        } else {
            when += end.getMinute() + ":";
        }
        if (end.getSecond() < 10) {
            when += "0" + end.getSecond();
        } else {
            when += end.getSecond();
        }
        return when;
    }
}
