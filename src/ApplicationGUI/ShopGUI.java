package ApplicationGUI;

import ApplicationLogic.Activity;
import ApplicationLogic.Calendar;
import ApplicationLogic.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Map;

public class ShopGUI extends Application {
    private Calendar calendar;

    public ShopGUI(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void start(Stage shopStage) throws Exception {
        //Shop Label
        Label shopLabel = new Label("SHOP");
        shopLabel.setFont(Font.font(25));

        //Clocks Label
        Label clocksLabel = new Label();
        clocksLabel.setFont(Font.font(25));

        //Passing FileInputStream object as a parameter
//        Image clockIcon = new Image(new File("/src/ApplicationGUI/clockIcon.png").toURI().toURL().toExternalForm());
//        ImageView clockIconView = new ImageView(clockIcon);
//        clockIconView.setFitWidth(25);
//        clockIconView.setFitHeight(25);

        //Add activity Button
        Button addActivityButton = new Button("Add Activity");
        addActivityButton.setMinSize(50, 20);
        addActivityButton.setOnAction(event -> {
            try {
                new AddActivityGUI(calendar.getUser().getActivitiesInShop(), calendar).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //Del Activity Button
        Button delActivityButton = new Button("Del Activity");
        delActivityButton.setMinSize(50,20);
        delActivityButton.setOnAction(event -> {
            try {
                new DelActivityGUI(calendar.getUser().getActivitiesInShop(), calendar).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //Informational labels and buttons TilePane
        TilePane shopLabelClocksAndButtonsTilePane = new TilePane(shopLabel, clocksLabel, addActivityButton, delActivityButton);
        shopLabelClocksAndButtonsTilePane.setTileAlignment(Pos.CENTER);
        shopLabelClocksAndButtonsTilePane.setAlignment(Pos.TOP_CENTER);
        shopLabelClocksAndButtonsTilePane.setOrientation(Orientation.HORIZONTAL);
        shopLabelClocksAndButtonsTilePane.setPrefRows(2);
        shopLabelClocksAndButtonsTilePane.setPrefColumns(2);

        //Activities in Shop TilePane
        TilePane activitiesInShopTilePane = makeActivitiesGroup(calendar.getUser(), clocksLabel);
        activitiesInShopTilePane.setTileAlignment(Pos.CENTER);
        activitiesInShopTilePane.setAlignment(Pos.CENTER);
        activitiesInShopTilePane.setOrientation(Orientation.HORIZONTAL);
        activitiesInShopTilePane.setPrefColumns(4);
        activitiesInShopTilePane.setHgap(5);
        activitiesInShopTilePane.setVgap(10);

        //Root Node
        VBox shopRoot = new VBox(shopLabelClocksAndButtonsTilePane, activitiesInShopTilePane);
        shopRoot.setAlignment(Pos.CENTER);
        shopRoot.setSpacing(10);
        shopRoot.setPadding(new Insets(10,20,10,20));

        Scene shopScene = new Scene(shopRoot);

        shopStage.setTitle("Shop");
        shopStage.setScene(shopScene);
        shopStage.show();
    }

    private TilePane makeActivitiesGroup(User user, Label clocksLabel) {
        clocksLabel.setText(user.getAmountOfClocks() + " clocks");

        TilePane activitiesInShopTilePane = new TilePane();

        for (Map.Entry<String, Activity> activitySet : user.getActivitiesInShop().entrySet()) {
            var activity = activitySet.getValue();
            Label activityName = new Label(activitySet.getKey());
            activityName.setWrapText(true);
            activityName.setMaxWidth(200);
            Label isDuty = new Label(activity.isDuty() ? "Duty" : "Pleasure");
            Label activityValue = new Label(activity.isDuty() ? "+" + activity.getValueInClocks() + " clocks" : "-" + activity.getValueInClocks() + " clocks");
            Button detailsButton = new Button("Details");
            detailsButton.setMinSize(30,20);
            detailsButton.setOnAction(event -> {
                try {
                    new ActivityDetailsGUI(activity).start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            activitiesInShopTilePane.getChildren().addAll(activityName, isDuty, activityValue, detailsButton);
        }
        return activitiesInShopTilePane;
    }
}
