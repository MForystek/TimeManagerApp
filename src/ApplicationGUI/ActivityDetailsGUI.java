package ApplicationGUI;

import ApplicationLogic.Activity;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ActivityDetailsGUI extends Application {
    private Activity activity;

    public ActivityDetailsGUI(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void start(Stage stageDetails) throws Exception {
        Label nameLabel = new Label("Name: ");
        Label descriptionLabel = new Label("Description: ");
        Label valueLabel = new Label("Value: ");
        Label importanceLabel = new Label("Importance: ");
        Label lengthLabel = new Label("Lenght: ");
        Label isDutyLabel = new Label("Duty: ");
        VBox activityLabels = new VBox(nameLabel, descriptionLabel, valueLabel, importanceLabel, lengthLabel, isDutyLabel);
        activityLabels.setPadding(new Insets(10,10,10,10));

        Label nameTextLabel = new Label(activity.getName());
        Label descriptionTextLabel = new Label(activity.getDescription());
        Label valueTextLabel = new Label(activity.getValueInClocks() + " clocks");
        Label importanceTextLabel = new Label(Short.toString(activity.getImportance()));
        Label lengthTextLabel = new Label(Integer.toString(activity.getRepetitionLengthInSec()));
        Label isDutyTextLabel = new Label(Boolean.toString(activity.isDuty()));
        VBox activityTextLabels = new VBox(nameTextLabel, descriptionTextLabel, valueTextLabel, importanceTextLabel, lengthTextLabel, isDutyTextLabel);
        activityTextLabels.setPadding(new Insets(10,10,10,10));

        HBox root = new HBox(activityLabels, activityTextLabels);
        root.setSpacing(10);

        Scene detailsScene = new Scene(root);
        stageDetails.setTitle(activity.getName() + " - details");
        stageDetails.setScene(detailsScene);
        stageDetails.show();
    }
}
