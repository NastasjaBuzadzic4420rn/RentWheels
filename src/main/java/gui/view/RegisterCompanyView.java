package gui.view;

import gui.controller.RegisterCompanyController;
import gui.controller.RegisterManagerController;
import gui.restclient.dto.ManagerCreateDto;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class RegisterCompanyView extends VBox {

    private Label titleLabel;
    private GridPane gridPane;
    private TextField companyNameField;
    private Label companyNameLabel;
    private TextArea descriptionField;
    private Label descriptionLabel;
    private TextField cityField;
    private Label cityLabel;
    private Button registerButton;
    private Label lblWarning;

    public RegisterCompanyView() {
        setAlignment(Pos.CENTER);
        setSpacing(10);
        setPadding(new Insets(20));

        initViewElements();
        addViewElements();
        initListener();
    }

    private void initViewElements() {
        titleLabel = new Label("Register your company");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        companyNameLabel = new Label("Company name:");
        companyNameField = new TextField();
        companyNameField.setPromptText("Enter company name");

        descriptionLabel = new Label("Description:");
        descriptionField = new TextArea();
        descriptionField.setMaxWidth(160);
        descriptionField.setPromptText("Enter company description");

        cityLabel = new Label("City:");
        cityField = new TextField();
        cityField.setPromptText("Enter city");

        lblWarning = new Label("All fields are required");
        lblWarning.setTextFill(Color.RED);
        lblWarning.setVisible(false);

        registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
    }

    private void addViewElements() {
        getChildren().add(titleLabel);

        gridPane.add(companyNameLabel, 0, 0);
        gridPane.add(companyNameField, 1, 0);
        gridPane.add(descriptionLabel, 0, 1);
        gridPane.add(descriptionField, 1, 1);
        gridPane.add(cityLabel, 0, 2);
        gridPane.add(cityField, 1, 2);

        VBox.setMargin(gridPane, new Insets(20));
        getChildren().addAll(gridPane, lblWarning, registerButton);
    }

    private void initListener(){
        registerButton.setOnAction(new RegisterCompanyController(this));
    }


    public TextField getCompanyNameField() {
        return companyNameField;
    }

    public TextArea getDescriptionField() {
        return descriptionField;
    }

    public TextField getCityField() {
        return cityField;
    }

    public Label getLblWarning() {
        return lblWarning;
    }
}
