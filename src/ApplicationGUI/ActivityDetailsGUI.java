package ApplicationGUI;

import ApplicationLogic.Activity;
import ApplicationLogic.OneTimeActivity;
import ApplicationLogic.ProjectActivity;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class ActivityDetailsGUI extends Application {
    private Activity activity;

    public ActivityDetailsGUI(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void start(Stage stageDetails) throws Exception {
        Label typeOfActivityLabel = new Label("Type: ");
        Label nameLabel = new Label("Name: ");
        Label descriptionLabel = new Label("Description: ");
        Label valueLabel = new Label("Value: ");
        Label importanceLabel = new Label("Importance: ");
        Label lengthLabel = new Label("Lenght: ");
        Label totalLengthLabel = new Label("Total length: ");
        Label deadlineLabel = new Label("Deadline: ");
        Label isDutyLabel = new Label("Duty: ");

        VBox activityLabels = new VBox();
        activityLabels.setPadding(new Insets(10,10,10,10));

        Label typeOfActivityTextField = new Label();
        Label nameTextLabel = new Label(activity.getName());
        Label descriptionTextLabel = new Label(activity.getDescription());
        Label valueTextLabel = new Label(activity.getValueInClocks() + " clocks");
        Label importanceTextLabel = new Label(Integer.toString(activity.getImportance()));
        Label lengthTextLabel = new Label(Integer.toString(activity.getActivityDurationInSec()));
        Label totalLengthTextLabel = new Label();
        Label deadlineTextLabel = new Label();
        Label isDutyTextLabel = new Label(Boolean.toString(activity.isDuty()));

        VBox activityTextLabels = new VBox();
        activityTextLabels.setPadding(new Insets(10,10,10,10));

        if (activity instanceof ProjectActivity) {
            activityLabels.getChildren().addAll(typeOfActivityLabel, nameLabel, descriptionLabel, valueLabel,
                    importanceLabel, lengthLabel, totalLengthLabel, deadlineLabel, isDutyLabel);
            typeOfActivityTextField.setText("Project");
            totalLengthTextLabel.setText(Integer.toString(((ProjectActivity)activity).getTotalDurationInSec()));
            deadlineTextLabel.setText(((ProjectActivity)activity).getDeadline().format(DateTimeFormatter.ofPattern("dd-MM-uuuu")));
            activityTextLabels.getChildren().addAll(typeOfActivityTextField, nameTextLabel, descriptionTextLabel,
                    valueTextLabel, importanceTextLabel, lengthTextLabel, totalLengthTextLabel, deadlineTextLabel, isDutyTextLabel);
        } else if (activity instanceof OneTimeActivity) {
            activityLabels.getChildren().addAll(typeOfActivityLabel, nameLabel, descriptionLabel, valueLabel,
                    importanceLabel, lengthLabel, deadlineLabel, isDutyLabel);
            typeOfActivityTextField.setText("One-Time");
            deadlineTextLabel.setText(((OneTimeActivity)activity).getDeadline().format(DateTimeFormatter.ofPattern("dd-MM-uuuu")));
            activityTextLabels.getChildren().addAll(typeOfActivityTextField, nameTextLabel, descriptionTextLabel,
                    valueTextLabel, importanceTextLabel, lengthTextLabel, deadlineTextLabel, isDutyTextLabel);
        } else {
            activityLabels.getChildren().addAll(typeOfActivityLabel, nameLabel, descriptionLabel, valueLabel,
                    importanceLabel, lengthLabel, isDutyLabel);
            typeOfActivityTextField.setText("Periodic");
            activityTextLabels.getChildren().addAll(typeOfActivityTextField, nameTextLabel, descriptionTextLabel,
                    valueTextLabel, importanceTextLabel, lengthTextLabel, isDutyTextLabel);
        }

        HBox root = new HBox(activityLabels, activityTextLabels);
        root.setSpacing(10);

        Scene detailsScene = new Scene(root);
        stageDetails.setTitle(activity.getName() + " - details");
        stageDetails.setScene(detailsScene);
        stageDetails.show();
    }
}
