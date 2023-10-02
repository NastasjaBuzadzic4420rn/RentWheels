package gui.view;

import gui.Main;
import gui.controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;


public class LoginView extends VBox {

    private GridPane gridPane;
    private Label welcomeLabel;
    private Label emailLabel;
    private Label passwordLabel;
    private TextField emailField;
    private PasswordField passwordField;
    private Button loginButton;
    private VBox textBox;
    private TextFlow registerClientTextFlow;
    private Text registerClientText;
    private Hyperlink registerClientHyperlink;
    private TextFlow registerCompanyTextFlow;
    private Text registerCompanyText;
    private Hyperlink registerCompanyHyperlink;

    public LoginView(){
        setSpacing(15);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10, 20, 0, 20));

        initViewElements();
        addViewElements();
        addListeners();
    }

    private void initViewElements() {
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);


        welcomeLabel = new Label("Welcome to RentWheels");
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        GridPane.setHalignment(welcomeLabel, javafx.geometry.HPos.CENTER);
        welcomeLabel.setPadding(new Insets(20));

        emailLabel = new Label("Email:");
        emailField = new TextField();
        passwordLabel = new Label("Password:");
        passwordField = new PasswordField();

        loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        textBox = new VBox();
        textBox.setPadding(new Insets(20));

        registerClientTextFlow = new TextFlow();
        registerClientTextFlow.setTextAlignment(TextAlignment.CENTER);
        registerClientText = new Text("Don't have an account? You can register ");
        registerClientHyperlink = new Hyperlink("here");

        registerCompanyTextFlow = new TextFlow();
        registerCompanyTextFlow.setTextAlignment(TextAlignment.CENTER);
        registerCompanyText = new Text("You can register new company and become manager ");
        registerCompanyHyperlink = new Hyperlink("here");


    }

    private void addViewElements() {
        gridPane.add(welcomeLabel, 0, 0, 2, 1);
        gridPane.add(emailLabel, 0, 1);
        gridPane.add(emailField, 1, 1);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(passwordField, 1, 2);

        registerClientTextFlow.getChildren().addAll(registerClientText, registerClientHyperlink);
        registerCompanyTextFlow.getChildren().addAll(registerCompanyText, registerCompanyHyperlink);

        textBox.getChildren().addAll(registerClientTextFlow, registerCompanyTextFlow);

        this.getChildren().addAll(gridPane, loginButton, textBox);

    }

    private void addListeners() {
        loginButton.setOnAction(new LoginController(emailField, passwordField));
        registerClientHyperlink.setOnAction(e -> {
            Scene sc = new Scene(new RegisterClientView(), 400, 400);
            Main.mainStage.setScene(sc);
            Main.mainStage.show();
            Main.mainStage.setTitle("RentWheels");
        });
        registerCompanyHyperlink.setOnAction(e -> {
            Scene sc = new Scene(new RegisterManagerView(), 400, 400);
            Main.mainStage.setScene(sc);
            Main.mainStage.show();
            Main.mainStage.setTitle("RentWheels");
        });
    }

}
