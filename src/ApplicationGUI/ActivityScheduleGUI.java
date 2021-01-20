package ApplicationGUI;

import ApplicationLogic.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ActivityScheduleGUI extends Application implements IObservable {
    private Calendar calendar;
    private Activity activity;
    private IObserver iObserver;

    public ActivityScheduleGUI(Calendar calendar, Activity activity) {
        this.calendar = calendar;
        this.activity = activity;
    }

    @Override
    public void addObserver(IObserver iObserver) {
        this.iObserver = iObserver;
    }

    @Override
    public void start(Stage scheduleStage) throws Exception {
        Label scheduleLabel = new Label("Schedule an Activity");
        Label chooseDateLabel = new Label("Choose date: ");

        //Date picking
        DatePicker datePicker = new DatePicker(LocalDate.now());
        Label dateInfoLabel = new Label("Date: " + datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-uuuu")));
        datePicker.setOnAction(event -> {
            if (datePicker.getValue().isBefore(LocalDate.now())
                    || datePicker.getValue().isAfter(LocalDate.now().plusDays(calendar.getUser().getCalendarLength()))
            ) {
                dateInfoLabel.setText("Date: Wrong Date!");
            } else if ((activity instanceof OneTimeActivity && datePicker.getValue().isAfter(((OneTimeActivity) activity).getDeadline()))
                    || (activity instanceof ProjectActivity && datePicker.getValue().isAfter(((ProjectActivity) activity).getDeadline()))
            ) {
                dateInfoLabel.setText("Date: After Deadline!");
            } else {
                dateInfoLabel.setText("Date: " + datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-uuuu")));
            }
        });

        //Hour picking
        Label hourLabel = new Label("Hour: ");
        TextField hourTextField = new TextField();

        //Minute picking
        Label minuteLabel = new Label("Minute: ");
        TextField minuteTextField = new TextField();

        //Second picking
        Label secondLabel = new Label("Second: ");
        TextField secondTextField = new TextField();

        //InformationLabel
        Label informationLabel = new Label();

        //Schedule Button
        Button scheduleButton = new Button("Schedule");
        scheduleButton.setMinSize(70,30);
        scheduleButton.setOnAction(event -> {
            if (!_isNonNegativeInteger(hourTextField.getText())) {
                informationLabel.setText("Invalid input");
            } else if (Integer.parseInt(hourTextField.getText()) > 23) {
                informationLabel.setText("That hour doesn't exist");
            } else if (!_isNonNegativeInteger(minuteTextField.getText())) {
                informationLabel.setText("Invalid input");
            } else if (Integer.parseInt(minuteTextField.getText()) > 59) {
                informationLabel.setText("That minute doesn't exist");
            } else if (!_isNonNegativeInteger(secondTextField.getText())) {
                informationLabel.setText("Invalid input");
            } else if (Integer.parseInt(secondTextField.getText()) > 59) {
                informationLabel.setText("That second doesn't exist");
            } else if (dateInfoLabel.getText().equals("Date: After Deadline!")
                    || dateInfoLabel.getText().equals("Date: Wrong Date!")
                    || calendar.putSegment(calendar.getDayByDate(datePicker.getValue()), activity.getNextSegment(
                        LocalTime.of(Integer.parseInt(hourTextField.getText()), Integer.parseInt(minuteTextField.getText()),
                                Integer.parseInt(secondTextField.getText())).toSecondOfDay())) == 1
                ) {
                    informationLabel.setText("Not enough time or wrong date");
            } else {
                    informationLabel.setText("Activity planned successfully");
                    notifyObserver();
                    scheduleStage.close();
            }
        });

        //Root Node
        VBox rootVBox = new VBox(scheduleLabel, chooseDateLabel, datePicker, dateInfoLabel, hourLabel, hourTextField,
                minuteLabel, minuteTextField, secondLabel, secondTextField, informationLabel, scheduleButton
        );
        rootVBox.setPadding(new Insets(5,20,5,20));
        rootVBox.setSpacing(5);

        Scene scene = new Scene(rootVBox);

        scheduleStage.setTitle("Schedule");
        scheduleStage.setScene(scene);
        scheduleStage.setMinHeight(370);
        scheduleStage.setResizable(false);
        scheduleStage.show();
    }

    @Override
    public void notifyObserver() {
        iObserver.update();
    }

    private boolean _isNonNegativeInteger(String string) {
        boolean good = true;
        try {
            var number = Integer.parseInt(string);
            if (number < 0) {
                good = false;
            }
        } catch (NumberFormatException e) {
            good = false;
        }
        return good;
    }
}
