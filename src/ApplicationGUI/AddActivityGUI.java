package ApplicationGUI;

import ApplicationLogic.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Map;

public class AddActivityGUI extends Application implements IObservable {
    private Map<String, Activity> activities;
    private IActivityShopAddDel activityAdder;
    private IObserver iObserver;
    private GridPane activitiesInShopGridPane;

    public AddActivityGUI(Map<String, Activity> activities, IActivityShopAddDel activityAdder, GridPane activitiesInShopGridPane) {
        this.activities = activities;
        this.activityAdder = activityAdder;
        this.activitiesInShopGridPane = activitiesInShopGridPane;
    }

    @Override
    public void setObserver(IObserver iObserver) {
        this.iObserver = iObserver;
    }

    @Override
    public void notifyObserver() {
        iObserver.update();
    }

    @Override
    public void start(Stage addStage) throws Exception {
        ToggleGroup activityTypeRadioButtons = new ToggleGroup();

//PERIODIC ACTIVITY
        Label periodicLabelForUser = new Label("Specify Activity details");
        periodicLabelForUser.setFont(Font.font(16));

        RadioButton periodicPeriodicActivityRadioButton = new RadioButton("Periodic");
        periodicPeriodicActivityRadioButton.fire();
        RadioButton periodicProjectActivityRadioButton = new RadioButton("Project");
        RadioButton periodicOneTimeActivityRadioButton = new RadioButton("One-Time");
        activityTypeRadioButtons.getToggles().addAll(periodicPeriodicActivityRadioButton, periodicProjectActivityRadioButton, periodicOneTimeActivityRadioButton);

        //Labels
        Label periodicNameLabel = new Label("Name:");
        Label periodicDescriptionLabel = new Label("Description:");
        Label periodicValueLabel = new Label("Value:");
        Label periodicImportanceLabel = new Label("Importance:");
        Label periodicSegmentLengthLabel = new Label("Pref Segment Length:");
        Label periodicResultLabel = new Label();

        //Fields to interaction
        TextField periodicNameTextField = new TextField();
        TextField periodicDescriptionTextField = new TextField();
        TextField periodicValueTextField = new TextField();
        TextField periodicImportanceTextField = new TextField();
        TextField periodicSegmentLengthTextField = new TextField();
        ToggleGroup periodicIsDutyRadioButtons = new ToggleGroup();
        RadioButton periodicDutyRadioButton = new RadioButton("Duty");
        periodicDutyRadioButton.fire();
        RadioButton periodicPleasureRadioButton = new RadioButton("Pleasure");
        periodicIsDutyRadioButtons.getToggles().addAll(periodicDutyRadioButton, periodicPleasureRadioButton);

        Button addPeriodicButton = new Button("Add");
        addPeriodicButton.setMinSize(50, 20);
        addPeriodicButton.setOnAction(event -> {
            if (!periodicNameTextField.getText().isEmpty() && !periodicDescriptionTextField.getText().isEmpty()
                    && _isPositiveInteger(periodicValueTextField.getText()) && _isPositiveInteger(periodicImportanceTextField.getText())
                    && _isPositiveInteger(periodicSegmentLengthTextField.getText())
            ) {
                PeriodicActivity newPeriodicActivity;
                if (periodicDutyRadioButton.isSelected()) {
                    newPeriodicActivity = (PeriodicActivity) ActivityFactory.makePeriodicActivity(
                            periodicNameTextField.getText(),
                            periodicDescriptionTextField.getText(),
                            periodicValueTextField.getText(),
                            periodicImportanceTextField.getText(),
                            periodicSegmentLengthTextField.getText(),
                            "true"
                    );
                } else {
                    newPeriodicActivity = (PeriodicActivity) ActivityFactory.makePeriodicActivity(
                            periodicNameTextField.getText(),
                            periodicDescriptionTextField.getText(),
                            periodicValueTextField.getText(),
                            periodicImportanceTextField.getText(),
                            periodicSegmentLengthTextField.getText(),
                            "false"
                    );
                }
                if (activityAdder.addActivityToShop(newPeriodicActivity) == 0) {
                    iObserver.update();
                    addStage.close();
                } else {
                    periodicResultLabel.setText("There already is an activity with this name in Shop");
                }
            } else {
                periodicResultLabel.setText("Not all fields have correct input!");
            }
        });

        GridPane periodicGridPane = new GridPane();
        periodicGridPane.add(periodicNameLabel, 0, 0);
        periodicGridPane.add(periodicDescriptionLabel, 0, 1);
        periodicGridPane.add(periodicValueLabel, 0, 2);
        periodicGridPane.add(periodicImportanceLabel, 0, 3);
        periodicGridPane.add(periodicSegmentLengthLabel, 0, 4);
        periodicGridPane.add(periodicDutyRadioButton, 0, 5);
        periodicGridPane.add(periodicNameTextField, 1, 0);
        periodicGridPane.add(periodicDescriptionTextField, 1, 1);
        periodicGridPane.add(periodicValueTextField, 1, 2);
        periodicGridPane.add(periodicImportanceTextField, 1, 3);
        periodicGridPane.add(periodicSegmentLengthTextField, 1, 4);
        periodicGridPane.add(periodicPleasureRadioButton, 1, 5);
        periodicGridPane.setHgap(5);
        periodicGridPane.setVgap(5);
        periodicGridPane.setAlignment(Pos.CENTER);

        //Activity type Buttons HBox
        HBox periodicActivityTypesRadioButtons = new HBox(periodicPeriodicActivityRadioButton, periodicProjectActivityRadioButton, periodicOneTimeActivityRadioButton);
        periodicActivityTypesRadioButtons.setSpacing(10);
        periodicActivityTypesRadioButtons.setAlignment(Pos.CENTER);

        //Periodic Root
        VBox periodicRoot = new VBox(periodicLabelForUser, periodicActivityTypesRadioButtons, periodicGridPane, periodicResultLabel, addPeriodicButton);
        periodicRoot.setAlignment(Pos.CENTER);
        periodicRoot.setSpacing(10);
        periodicRoot.setPadding(new Insets(10,10,10,10));

//PROJECT ACTIVITY
        Label projectLabelForUser = new Label("Specify Activity details");
        projectLabelForUser.setFont(Font.font(16));

        RadioButton projectPeriodicActivityRadioButton = new RadioButton("Periodic");
        RadioButton projectProjectActivityRadioButton = new RadioButton("Project");
        RadioButton projectOneTimeActivityRadioButton = new RadioButton("One-Time");
        activityTypeRadioButtons.getToggles().addAll(projectPeriodicActivityRadioButton, projectProjectActivityRadioButton, projectOneTimeActivityRadioButton);

        //Labels
        Label projectNameLabel = new Label("Name:");
        Label projectDescriptionLabel = new Label("Description:");
        Label projectValueLabel = new Label("Value:");
        Label projectImportanceLabel = new Label("Importance:");
        Label projectSegmentLengthLabel = new Label("Pref Segment Length:");
        Label projectTotalLengthLabel = new Label("Total Length:");
        Label projectDeadlineLabel = new Label("Deadline:");
        Label projectResultLabel = new Label();

        //Fields to interaction
        TextField projectNameTextField = new TextField();
        TextField projectDescriptionTextField = new TextField();
        TextField projectValueTextField = new TextField();
        TextField projectImportanceTextField = new TextField();
        TextField projectSegmentLengthTextField = new TextField();
        TextField projectTotalLengthTextField = new TextField();
        DatePicker projectDeadlinePicker = new DatePicker();
        ToggleGroup projectIsDutyRadioButtons = new ToggleGroup();
        RadioButton projectDutyRadioButton = new RadioButton("Duty");
        projectDutyRadioButton.fire();
        RadioButton projectPleasureRadioButton = new RadioButton("Pleasure");
        projectIsDutyRadioButtons.getToggles().addAll(projectDutyRadioButton, projectPleasureRadioButton);

        Button addProjectButton = new Button("Add");
        addProjectButton.setMinSize(50, 20);
        addProjectButton.setOnAction(event -> {
            if (!projectNameTextField.getText().isEmpty() && !projectDescriptionTextField.getText().isEmpty()
                    && _isPositiveInteger(projectValueTextField.getText()) && _isPositiveInteger(projectImportanceTextField.getText())
                    && projectDeadlinePicker.getValue() != null && !projectDeadlinePicker.getValue().isBefore(LocalDate.now())
                    && _isAppropriateTotalAndSegmentLength(projectTotalLengthTextField.getText(), projectSegmentLengthTextField.getText())
            ) {
                ProjectActivity newProjectActivity;
                if (projectDutyRadioButton.isSelected()) {
                    newProjectActivity = (ProjectActivity) ActivityFactory.makeProjectActivity(
                            projectNameTextField.getText(),
                            projectDescriptionTextField.getText(),
                            projectValueTextField.getText(),
                            projectImportanceTextField.getText(),
                            projectSegmentLengthTextField.getText(),
                            "true",
                            projectTotalLengthTextField.getText(),
                            projectDeadlinePicker.getValue()
                    );
                } else {
                    newProjectActivity = (ProjectActivity) ActivityFactory.makeProjectActivity(
                            projectNameTextField.getText(),
                            projectDescriptionTextField.getText(),
                            projectValueTextField.getText(),
                            projectImportanceTextField.getText(),
                            projectSegmentLengthTextField.getText(),
                            "false",
                            projectTotalLengthTextField.getText(),
                            projectDeadlinePicker.getValue()
                    );
                }
                if (activityAdder.addActivityToShop(newProjectActivity) == 0) {
                    iObserver.update();
                    addStage.close();
                } else {
                    projectResultLabel.setText("There already is an activity with this name in Shop");
                }
            } else {
                projectResultLabel.setText("Not all fields have correct input!");
            }
        });

        GridPane projectGridPane = new GridPane();
        projectGridPane.add(projectNameLabel, 0, 0);
        projectGridPane.add(projectDescriptionLabel, 0, 1);
        projectGridPane.add(projectValueLabel, 0, 2);
        projectGridPane.add(projectImportanceLabel, 0, 3);
        projectGridPane.add(projectSegmentLengthLabel, 0, 4);
        projectGridPane.add(projectTotalLengthLabel, 0, 5);
        projectGridPane.add(projectDeadlineLabel, 0, 6);
        projectGridPane.add(projectDutyRadioButton, 0, 7);
        projectGridPane.add(projectNameTextField, 1, 0);
        projectGridPane.add(projectDescriptionTextField, 1, 1);
        projectGridPane.add(projectValueTextField, 1, 2);
        projectGridPane.add(projectImportanceTextField, 1, 3);
        projectGridPane.add(projectSegmentLengthTextField, 1, 4);
        projectGridPane.add(projectTotalLengthTextField, 1, 5);
        projectGridPane.add(projectDeadlinePicker, 1, 6);
        projectGridPane.add(projectPleasureRadioButton, 1, 7);
        projectGridPane.setHgap(5);
        projectGridPane.setVgap(5);
        projectGridPane.setAlignment(Pos.CENTER);

        //Activity type Buttons HBox
        HBox projectActivityTypesRadioButtons = new HBox(projectPeriodicActivityRadioButton, projectProjectActivityRadioButton, projectOneTimeActivityRadioButton);
        projectActivityTypesRadioButtons.setSpacing(10);
        projectActivityTypesRadioButtons.setAlignment(Pos.CENTER);

        //Project Root
        VBox projectRoot = new VBox(projectLabelForUser,  projectActivityTypesRadioButtons, projectGridPane, projectResultLabel, addProjectButton);
        projectRoot.setAlignment(Pos.CENTER);
        projectRoot.setSpacing(10);
        projectRoot.setPadding(new Insets(10,10,10,10));

//ONE TIME ACTIVITY
        Label oneTimeLabelForUser = new Label("Specify Activity details");
        oneTimeLabelForUser.setFont(Font.font(16));

        RadioButton oneTimePeriodicActivityRadioButton = new RadioButton("Periodic");
        RadioButton oneTimeProjectActivityRadioButton = new RadioButton("Project");
        RadioButton oneTimeOneTimeActivityRadioButton = new RadioButton("One-Time");
        activityTypeRadioButtons.getToggles().addAll(oneTimePeriodicActivityRadioButton, oneTimeProjectActivityRadioButton, oneTimeOneTimeActivityRadioButton);

        //Labels
        Label oneTimeNameLabel = new Label("Name:");
        Label oneTimeDescriptionLabel = new Label("Description:");
        Label oneTimeValueLabel = new Label("Value:");
        Label oneTimeImportanceLabel = new Label("Importance:");
        Label oneTimeTotalLengthLabel = new Label("Total Length:");
        Label oneTimeDeadlineLabel = new Label("Deadline:");
        Label oneTimeResultLabel = new Label();

        //Fields to interaction
        TextField oneTimeNameTextField = new TextField();
        TextField oneTimeDescriptionTextField = new TextField();
        TextField oneTimeValueTextField = new TextField();
        TextField oneTimeImportanceTextField = new TextField();
        TextField oneTimeTotalLengthTextField = new TextField();
        DatePicker oneTimeDeadlinePicker = new DatePicker();
        ToggleGroup oneTimeIsDutyRadioButtons = new ToggleGroup();
        RadioButton oneTimeDutyRadioButton = new RadioButton("Duty");
        oneTimeDutyRadioButton.fire();
        RadioButton oneTimePleasureRadioButton = new RadioButton("Pleasure");
        oneTimeIsDutyRadioButtons.getToggles().addAll(oneTimeDutyRadioButton, oneTimePleasureRadioButton);

        Button addOneTimeButton = new Button("Add");
        addOneTimeButton.setMinSize(50, 20);
        addOneTimeButton.setOnAction(event -> {
            if (!oneTimeNameTextField.getText().isEmpty() && !oneTimeDescriptionTextField.getText().isEmpty()
                    && _isPositiveInteger(oneTimeValueTextField.getText()) && _isPositiveInteger(oneTimeImportanceTextField.getText())
                    && oneTimeDeadlinePicker.getValue() != null && !oneTimeDeadlinePicker.getValue().isBefore(LocalDate.now())
                    && _isPositiveInteger(oneTimeTotalLengthTextField.getText())
            ) {
                OneTimeActivity newOneTimeActivity;
                if (oneTimeDutyRadioButton.isSelected()) {
                    newOneTimeActivity = (OneTimeActivity) ActivityFactory.makeOneTimeActivity(
                            oneTimeNameTextField.getText(),
                            oneTimeDescriptionTextField.getText(),
                            oneTimeValueTextField.getText(),
                            oneTimeImportanceTextField.getText(),
                            oneTimeTotalLengthTextField.getText(),
                            "true",
                            oneTimeDeadlinePicker.getValue()
                    );
                } else {
                    newOneTimeActivity = (OneTimeActivity) ActivityFactory.makeOneTimeActivity(
                            oneTimeNameTextField.getText(),
                            oneTimeDescriptionTextField.getText(),
                            oneTimeValueTextField.getText(),
                            oneTimeImportanceTextField.getText(),
                            oneTimeTotalLengthTextField.getText(),
                            "false",
                            oneTimeDeadlinePicker.getValue()
                    );
                }
                if (activityAdder.addActivityToShop(newOneTimeActivity) == 0) {
                    iObserver.update();
                    addStage.close();
                } else {
                    oneTimeResultLabel.setText("There already is an activity with this name in Shop");
                }
            } else {
                oneTimeResultLabel.setText("Not all fields have correct input!");
            }
        });

        GridPane oneTimeGridPane = new GridPane();
        oneTimeGridPane.add(oneTimeNameLabel, 0, 0);
        oneTimeGridPane.add(oneTimeDescriptionLabel, 0, 1);
        oneTimeGridPane.add(oneTimeValueLabel, 0, 2);
        oneTimeGridPane.add(oneTimeImportanceLabel, 0, 3);
        oneTimeGridPane.add(oneTimeTotalLengthLabel, 0, 4);
        oneTimeGridPane.add(oneTimeDeadlineLabel, 0, 5);
        oneTimeGridPane.add(oneTimeDutyRadioButton, 0, 6);
        oneTimeGridPane.add(oneTimeNameTextField, 1, 0);
        oneTimeGridPane.add(oneTimeDescriptionTextField, 1, 1);
        oneTimeGridPane.add(oneTimeValueTextField, 1, 2);
        oneTimeGridPane.add(oneTimeImportanceTextField, 1, 3);
        oneTimeGridPane.add(oneTimeTotalLengthTextField, 1, 4);
        oneTimeGridPane.add(oneTimeDeadlinePicker, 1, 5);
        oneTimeGridPane.add(oneTimePleasureRadioButton, 1, 6);
        oneTimeGridPane.setHgap(5);
        oneTimeGridPane.setVgap(5);
        oneTimeGridPane.setAlignment(Pos.CENTER);

        //Activity type Buttons HBox
        HBox oneTimeActivityTypesRadioButtons = new HBox(oneTimePeriodicActivityRadioButton, oneTimeProjectActivityRadioButton, oneTimeOneTimeActivityRadioButton);
        oneTimeActivityTypesRadioButtons.setSpacing(10);
        oneTimeActivityTypesRadioButtons.setAlignment(Pos.CENTER);

        //One Time Root
        VBox oneTimeRoot = new VBox(oneTimeLabelForUser, oneTimeActivityTypesRadioButtons, oneTimeGridPane, oneTimeResultLabel, addOneTimeButton);
        oneTimeRoot.setAlignment(Pos.CENTER);
        oneTimeRoot.setSpacing(10);
        oneTimeRoot.setPadding(new Insets(10,10,10,10));

        Scene periodicScene = new Scene(periodicRoot, 300, 340 );
        Scene projectScene = new Scene(projectRoot, 350, 390);
        Scene oneTimeScene = new Scene(oneTimeRoot, 320, 350);

        periodicProjectActivityRadioButton.setOnAction(event -> {
            addStage.setScene(projectScene);
            projectProjectActivityRadioButton.fire();
            projectProjectActivityRadioButton.requestFocus();
        });
        periodicOneTimeActivityRadioButton.setOnAction(event -> {
            addStage.setScene(oneTimeScene);
            oneTimeOneTimeActivityRadioButton.fire();
            oneTimeOneTimeActivityRadioButton.requestFocus();
        });
        projectPeriodicActivityRadioButton.setOnAction(event -> {
            addStage.setScene(periodicScene);
            periodicPeriodicActivityRadioButton.fire();
            periodicPeriodicActivityRadioButton.requestFocus();
        });
        projectOneTimeActivityRadioButton.setOnAction(event -> {
            addStage.setScene(oneTimeScene);
            oneTimeOneTimeActivityRadioButton.fire();
            oneTimeOneTimeActivityRadioButton.requestFocus();
        });
        oneTimePeriodicActivityRadioButton.setOnAction(event -> {
            addStage.setScene(periodicScene);
            periodicPeriodicActivityRadioButton.fire();
            periodicPeriodicActivityRadioButton.requestFocus();
        });
        oneTimeProjectActivityRadioButton.setOnAction(event -> {
            addStage.setScene(projectScene);
            projectProjectActivityRadioButton.fire();
            projectProjectActivityRadioButton.requestFocus();
        });

        addStage.setTitle("Add Activity");
        addStage.setMaxWidth(320);
        addStage.setMaxHeight(370);
        addStage.setMinWidth(320);
        addStage.setMinHeight(370);
        addStage.setResizable(false);
        addStage.setScene(periodicScene);
        addStage.show();
    }

    private boolean _isPositiveInteger(String string) {
        boolean good = true;
        try {
            var number = Integer.parseInt(string);
            if (number <= 0) {
                good = false;
            }
        } catch (NumberFormatException e) {
            good = false;
        }
        return good;
    }

    private boolean _isAppropriateTotalAndSegmentLength(String totLen, String segLen) {
        return _isPositiveInteger(totLen) && _isPositiveInteger(segLen) && Integer.parseInt(totLen) >= Integer.parseInt(segLen);
    }
}
