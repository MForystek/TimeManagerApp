package ApplicationGUI;

import ApplicationLogic.Activity;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Map;

public class AddActivityGUI extends Application {
    private Map<String, Activity> activities;

    public AddActivityGUI(Map<String, Activity> activities) {
        this.activities = activities;
    }

    @Override
    public void start(Stage addStage) throws Exception {

    }
}
