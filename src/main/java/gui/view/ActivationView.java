package gui.view;

import gui.controller.ConfirmClientController;
import gui.controller.ConfirmManagerController;
import gui.restclient.dto.ManagerCreateDto;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ActivationView extends VBox {
    private Label titleLabel;
    private Label descriptionLabel;
    private TextField activationField;
    private Label lblWarning;
    private Button activateButton;

    private String type;
    private Long userId;

    public ActivationView(String type, Long userId) {
        this.userId = userId;
        this.type = type;

        setSpacing(10);
        setPadding(new Insets(20));

        initViewElements();
        addViewElements();
        initListener();
    }

    private void initViewElements() {
        titleLabel = new Label("Account Activation");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        descriptionLabel = new Label("Please enter the activation code sent to your email:");

        activationField = new TextField();
        activationField.setPromptText("Activation Code");

        lblWarning = new Label("Account is not activated. Try again.");
        lblWarning.setTextFill(Color.RED);
        lblWarning.setVisible(false);

        activateButton = new Button("Activate");
        activateButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        activateButton.setDefaultButton(true);
    }

    private void addViewElements() {
        getChildren().addAll(titleLabel, descriptionLabel, activationField, lblWarning, activateButton);
    }

    private void initListener() {
        if (type.equals("CLIENT")) {
            activateButton.setOnAction(new ConfirmClientController(this));
        } else if (type.equals("MANAGER")) {
            activateButton.setOnAction(new ConfirmManagerController(this));
        }
    }


    public TextField getActivationField() {
        return activationField;
    }

    public Label getLblWarning() {
        return lblWarning;
    }

    public Long getUserId() {
        return userId;
    }
}
