package gui.view;

import gui.Main;
import gui.controller.RegisterClientController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class RegisterClientView extends VBox {

    private GridPane gridPane;
    private Label titleLabel;
    private Label lblFirstName;
    private Label lblLastName;
    private Label lblEmail;
    private Label lblPassword;
    private Label lblUsername;

    private TextField tfFirstName;
    private TextField tfLastName;
    private TextField tfEmail;
    private TextField tfPassword;
    private TextField tfUsername;
    private Label lblWarning;
    private Button btnRegister;
    private TextFlow textFlow;
    private Text text;
    private Hyperlink hyperlink;

    public RegisterClientView(){
        setSpacing(25);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10, 20, 0, 20));

        initViewElements();
        addViewElements();
        addListeners();
    }

    private void initViewElements() {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        titleLabel = new Label("Registration");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        GridPane.setHalignment(titleLabel, HPos.CENTER);
        titleLabel.setPadding(new Insets(20));

        lblFirstName = new Label("First name:");
        lblLastName = new Label("Last name:");
        lblEmail = new Label("Email:");
        lblUsername = new Label("Username:");
        lblPassword = new Label("Password:");
        lblWarning = new Label("All fields are required");
        lblWarning.setTextFill(Color.RED);
        lblWarning.setVisible(false);

        tfFirstName = new TextField();
        tfLastName = new TextField();
        tfEmail = new TextField();
        tfUsername = new TextField();
        tfPassword = new PasswordField();

        btnRegister = new Button("Register");
        btnRegister.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        textFlow = new TextFlow();
        textFlow.setTextAlignment(TextAlignment.CENTER);
        textFlow.setPadding(new Insets(20));
        text = new Text("Already have an account? You can login ");
        hyperlink = new Hyperlink("here");
    }

    private void addViewElements() {
        gridPane.add(titleLabel, 0, 0, 2, 1);
        gridPane.add(lblFirstName, 0, 1);
        gridPane.add(tfFirstName, 1, 1);
        gridPane.add(lblLastName, 0, 2);
        gridPane.add(tfLastName, 1, 2);
        gridPane.add(lblEmail, 0, 3);
        gridPane.add(tfEmail, 1, 3);
        gridPane.add(lblUsername, 0, 4);
        gridPane.add(tfUsername, 1, 4);
        gridPane.add(lblPassword, 0, 5);
        gridPane.add(tfPassword, 1, 5);

        textFlow.getChildren().addAll(text, hyperlink);

        this.getChildren().addAll(gridPane, lblWarning, btnRegister, textFlow);
    }

    private void addListeners() {
        btnRegister.setOnAction(new RegisterClientController(this));
        hyperlink.setOnAction(e -> {
            Scene sc = new Scene(new LoginView(), 400, 300);
            Main.mainStage.setScene(sc);
            Main.mainStage.show();
            Main.mainStage.setTitle("RentWheels");
        });
    }

    public TextField getTfFirstName() {
        return tfFirstName;
    }

    public TextField getTfLastName() {
        return tfLastName;
    }

    public TextField getTfEmail() {
        return tfEmail;
    }

    public TextField getTfPassword() {
        return tfPassword;
    }

    public TextField getTfUsername() {
        return tfUsername;
    }

    public Label getLblWarning() {
        return lblWarning;
    }
}
