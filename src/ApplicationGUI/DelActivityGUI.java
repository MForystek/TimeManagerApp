package ApplicationGUI;

import ApplicationLogic.Activity;
import ApplicationLogic.IActivityShopAddDel;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;

public class DelActivityGUI extends Application {
    private Map<String, Activity> activities;
    private IActivityShopAddDel activityDeleter;

    public DelActivityGUI(Map<String, Activity> activities, IActivityShopAddDel activityDeleter) {
        this.activities = activities;
        this.activityDeleter = activityDeleter;
    }

    @Override
    public void start(Stage delStage) throws Exception {
        Label labelForUser = new Label("Type the name of Activity you want to delete from the shop");
        TextField nameOfActivityToDeleteTextField = new TextField();
        Label isDoneLabel = new Label();
        Button delButton = new Button("Delete");
        delButton.setMinSize(50, 20);
        delButton.setOnAction(event -> {
            String activityName = nameOfActivityToDeleteTextField.getText();
            if (activityDeleter.delActivityFromShop(activityName) == 0) {
                isDoneLabel.setText("Activity " + activityName + " deleted successfully");
            } else {
                isDoneLabel.setText("There is no activity with name " + activityName);
            }
        });
        VBox root = new VBox(labelForUser, nameOfActivityToDeleteTextField, isDoneLabel, delButton);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(10,10,10,10));

        Scene scene = new Scene(root);

        delStage.setTitle("Delete Activity");
        delStage.setMinHeight(200);
        delStage.setScene(scene);
        delStage.show();

    }
}
