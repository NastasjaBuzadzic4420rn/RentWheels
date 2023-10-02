package gui.view;

import gui.Main;
import gui.controller.RegisterManagerController;
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

import java.time.LocalDate;

public class RegisterManagerView extends VBox {

    private GridPane managerGridPane;
    private Label titleManagerLabel;
    private Label lblFirstName;
    private Label lblLastName;
    private Label lblEmail;
    private Label lblPassword;
    private Label lblUsername;
    private Label lblBirthDate;

    private TextField tfFirstName;
    private TextField tfLastName;
    private TextField tfEmail;
    private TextField tfPassword;
    private TextField tfUsername;
    private DatePicker dpBirthDate;


    private Label lblWarning;
    private Button btnRegister;
    private TextFlow textFlow;
    private Text text;
    private Hyperlink hyperlink;

    public RegisterManagerView(){
        setSpacing(25);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10, 20, 0, 20));

        initViewElements();
        addViewElements();
        initListener();
    }

    private void initViewElements() {
        managerGridPane = new GridPane();
        managerGridPane.setVgap(10);
        managerGridPane.setHgap(10);
        managerGridPane.setAlignment(Pos.CENTER);

        titleManagerLabel = new Label("Register as manager");
        titleManagerLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        GridPane.setHalignment(titleManagerLabel, HPos.CENTER);
        titleManagerLabel.setPadding(new Insets(20));

        lblFirstName = new Label("First name:");
        lblLastName = new Label("Last name:");
        lblEmail = new Label("Email:");
        lblUsername = new Label("Username:");
        lblPassword = new Label("Password:");
        lblBirthDate = new Label("Date of birth: ");

        tfFirstName = new TextField();
        tfLastName = new TextField();
        tfEmail = new TextField();
        tfUsername = new TextField();
        tfPassword = new PasswordField();
        dpBirthDate = new DatePicker();
        dpBirthDate.setValue(LocalDate.now());

        lblWarning = new Label("All fields are required");
        lblWarning.setTextFill(Color.RED);
        lblWarning.setVisible(false);

        btnRegister = new Button("Register");
        btnRegister.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        textFlow = new TextFlow();
        textFlow.setTextAlignment(TextAlignment.CENTER);
        textFlow.setPadding(new Insets(20));
        text = new Text("Already have an account? You can login ");
        hyperlink = new Hyperlink("here");
    }

    private void addViewElements() {
        managerGridPane.add(titleManagerLabel, 0, 0, 2, 1);
        managerGridPane.add(lblFirstName, 0, 1);
        managerGridPane.add(tfFirstName, 1, 1);
        managerGridPane.add(lblLastName, 0, 2);
        managerGridPane.add(tfLastName, 1, 2);
        managerGridPane.add(lblEmail, 0, 3);
        managerGridPane.add(tfEmail, 1, 3);
        managerGridPane.add(lblUsername, 0, 4);
        managerGridPane.add(tfUsername, 1, 4);
        managerGridPane.add(lblPassword, 0, 5);
        managerGridPane.add(tfPassword, 1, 5);


        textFlow.getChildren().addAll(text, hyperlink);

        this.getChildren().addAll(managerGridPane, lblWarning, btnRegister, textFlow);
    }

    private void initListener() {
        btnRegister.setOnAction(new RegisterManagerController(this));
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

    public DatePicker getDpBirthDate() {
        return dpBirthDate;
    }

    public Label getLblWarning() {
        return lblWarning;
    }


}
