package ApplicationGUI;

import ApplicationLogic.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CalendarGUI extends Application {
    private User user;

    public CalendarGUI(User user) {
        this.user = user;
    }

    @Override
    public void start(Stage calendarStage) throws Exception {
        Label calendarLabel = new Label("CALENDAR");
        calendarLabel.setFont(Font.font(20));

        //Choosing shown date
        DatePicker chooseDayDatePicker = new DatePicker(LocalDate.now());

        //Date and additional date info Labels
        Label dateLabel = new Label("Date: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-uuuu")));
        dateLabel.setFont(Font.font(15));
        Label dateAdditionalInfoLabel = new Label("Today-" + LocalDate.now().getDayOfWeek().toString().toLowerCase());
        dateAdditionalInfoLabel.setFont(Font.font(15));

        GridPane informationalLabelsAndDatePicker = new GridPane();
        informationalLabelsAndDatePicker.addRow(0, calendarLabel, chooseDayDatePicker);
        informationalLabelsAndDatePicker.addRow(1, dateLabel, dateAdditionalInfoLabel);
        informationalLabelsAndDatePicker.setHgap(10);
        informationalLabelsAndDatePicker.setVgap(5);
        informationalLabelsAndDatePicker.setAlignment(Pos.TOP_CENTER);

        VBox activitiesInDayVBox = new VBox();

        //Calendar Root
        VBox calendarRoot = new VBox(informationalLabelsAndDatePicker, activitiesInDayVBox);
        calendarRoot.setAlignment(Pos.TOP_CENTER);
        calendarRoot.setPadding(new Insets(10,20,10,20));
        calendarRoot.setSpacing(10);

        Scene calendarScene = new Scene(calendarRoot);

        calendarStage.setTitle("Calendar");
        calendarStage.setScene(calendarScene);
        calendarStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
