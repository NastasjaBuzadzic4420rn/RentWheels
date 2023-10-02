package gui.view;

import gui.controller.EditController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class EditView extends GridPane {
//    private Label lblId;
    private Label lblFirstName;
    private Label lblLastName;
    private Label lblEmail;
    private Label lblPassword;
    private Label lblUsername;
    private Label lblPassport;

//    private TextField tfId;
    private TextField tfFirstName;
    private TextField tfLastName;
    private TextField tfEmail;
    private TextField tfPassword;
    private TextField tfUsername;
    private TextField tfPassport;

    private Button btnSave;

    public EditView(){
        initViewElements();
        addElements();
        addListeners();
        this.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setHgap(10);
    }

    private void addListeners() {
        this.btnSave.setOnAction(new EditController(this));
    }

    private void addElements() {
//        this.addRow(0, lblId, tfId);
        this.addRow(1, lblFirstName, tfFirstName);
        this.addRow(1, lblLastName, tfLastName);
        this.addRow(2, lblEmail, tfEmail);
        this.addRow(3, lblUsername, tfUsername);
        this.addRow(4, lblPassword, tfPassword);
        this.addRow(5, lblPassport, tfPassport);
        this.addRow(6, btnSave);
    }

    private void initViewElements() {
//        lblId = new Label("Id:");
        lblFirstName = new Label("First name:");
        lblLastName = new Label("Last name:");
        lblEmail = new Label("Email:");
        lblUsername = new Label("Username:");
        lblPassword = new Label("Password:");
        lblPassport = new Label("Passport number:");

//        tfId = new TextField();
        tfFirstName = new TextField();
        tfLastName = new TextField();
        tfEmail = new TextField();
        tfUsername = new TextField();
        tfPassword = new TextField();
        tfPassport = new TextField();

        btnSave = new Button("Save");
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

    public TextField getTfPassport() {
        return tfPassport;
    }

//    public TextField getTfId() {
//        return tfId;
//    }
}
