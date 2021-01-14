package ApplicationGUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CalendarGUI extends Application {
    @Override
    public void start(Stage calendarStage) throws Exception {
        Label calendarLabel = new Label("CALENDAR");

        Scene calendarScene = new Scene(calendarLabel);

        calendarStage.setTitle("Calendar");
        calendarStage.setScene(calendarScene);
        calendarStage.show();
    }
}
