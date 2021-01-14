package ApplicationGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsGUI extends Application {
    @Override
    public void start(Stage settingsStage) throws Exception {
        Label settingsLabel = new Label("SETTINGS");

        //Settings Root Node
        VBox settingsVBox = new VBox();
        settingsVBox.setSpacing(5);
        settingsVBox.setAlignment(Pos.TOP_CENTER);
        settingsVBox.setPadding(new Insets(10, 20, 10, 20));

        Scene settingsScene = new Scene(settingsVBox, 300, 200);

        settingsStage.setTitle("Settings");
        settingsStage.setScene(settingsScene);
        settingsStage.show();
    }
}
