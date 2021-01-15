package ApplicationGUI;

import ApplicationLogic.Activity;
import ApplicationLogic.Calendar;
import ApplicationLogic.IObserver;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Map;

public class ShopGUI extends Application implements IObserver {
    private Calendar calendar;
    private Label clocksLabel;
    private GridPane activitiesInShopGridPane;

    public ShopGUI(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void update() {
        activitiesInShopGridPane = _makeActivitiesGroup(activitiesInShopGridPane);
    }

    @Override
    public void start(Stage shopStage) throws Exception {
        //Shop Label
        Label shopLabel = new Label("SHOP");
        shopLabel.setFont(Font.font(25));

        //Clocks Label
        clocksLabel = new Label();
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
                var adder = new AddActivityGUI(calendar.getUser().getActivitiesInShop(), calendar, activitiesInShopGridPane);
                adder.start(new Stage());
                adder.setObserver(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //Informational labels and buttons TilePane
        TilePane shopLabelClocksAndButtonsTilePane = new TilePane(shopLabel, clocksLabel, addActivityButton);
        shopLabelClocksAndButtonsTilePane.setTileAlignment(Pos.CENTER);
        shopLabelClocksAndButtonsTilePane.setAlignment(Pos.TOP_CENTER);
        shopLabelClocksAndButtonsTilePane.setOrientation(Orientation.HORIZONTAL);
        shopLabelClocksAndButtonsTilePane.setPrefRows(1);
        shopLabelClocksAndButtonsTilePane.setPrefColumns(4);

        //Activities in Shop GridPane
        activitiesInShopGridPane = _makeActivitiesGroup(activitiesInShopGridPane);
        activitiesInShopGridPane.setAlignment(Pos.TOP_CENTER);
        activitiesInShopGridPane.autosize();
        activitiesInShopGridPane.setHgap(5);
        activitiesInShopGridPane.setVgap(10);

        //Shop main VBox
        VBox shopVBox = new VBox(shopLabelClocksAndButtonsTilePane, activitiesInShopGridPane);
        shopVBox.setAlignment(Pos.TOP_CENTER);
        shopVBox.setSpacing(10);
        shopVBox.setPadding(new Insets(10,20,10,20));

        //Root Node
        ScrollPane shopRoot = new ScrollPane(shopVBox);
        shopRoot.setFitToHeight(true);
        shopRoot.setMinSize(450, 400);

        Scene shopScene = new Scene(shopRoot);

        shopStage.setTitle("Shop");
        shopStage.setScene(shopScene);
        shopStage.setMinHeight(400);
        shopStage.setMinWidth(450);
        shopStage.show();
    }

    private GridPane _makeActivitiesGroup(GridPane activitiesInShopGridPane) {
        clocksLabel.setText(calendar.getUser().getAmountOfClocks() + " clocks");
        if (activitiesInShopGridPane == null) {
            activitiesInShopGridPane = new GridPane();
        }
        activitiesInShopGridPane.getChildren().clear();
        for (Map.Entry<String, Activity> activitySet : calendar.getUser().getActivitiesInShop().entrySet()) {
            var activity = activitySet.getValue();
            Label activityName = new Label(activitySet.getKey());
            activityName.setWrapText(true);
            activityName.setMaxWidth(200);
            Label isDuty = new Label(activity.isDuty() ? "Duty" : "Pleasure");
            Label activityValue = new Label(activity.isDuty() ? "+" + activity.getValueInClocks() + " clocks" : "-" + activity.getValueInClocks() + " clocks");

            //Activity details Button
            Button detailsButton = new Button("Details");
            detailsButton.setMinSize(70,30);
            detailsButton.setOnAction(event -> {
                try {
                    new ActivityDetailsGUI(activity).start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            //Buy activity Button
            Button buyButton = new Button("Buy");
            buyButton.setMinSize(70, 30);
            buyButton.setOnAction(event -> {

            });

            //Delete activity Button
            Button deleteButton = new Button("Delete");
            deleteButton.setMinSize(70, 30);
            GridPane finalActivitiesInShopGridPane = activitiesInShopGridPane;
            deleteButton.setOnAction(event -> {
                calendar.delActivityFromShop(activitySet.getKey());
                finalActivitiesInShopGridPane.getChildren().removeAll(activityName, isDuty, activityValue, detailsButton, buyButton, deleteButton);
            });

            activitiesInShopGridPane.addRow(activitiesInShopGridPane.getRowCount(), activityName, isDuty, activityValue, detailsButton, buyButton, deleteButton);
        }
        return activitiesInShopGridPane;
    }
}
