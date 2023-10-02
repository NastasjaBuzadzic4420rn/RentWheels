package gui.controller;


import gui.ClientApp;
import gui.Main;
import gui.model.User;
import gui.restclient.ReservationServiceRestClient;
import gui.restclient.UserServiceRestClient;
import gui.restclient.dto.ManagerCreateDto;
import gui.view.ActivationView;
import gui.view.AdminView;
import gui.view.ManagerView;
import gui.view.ReservationsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController implements EventHandler<ActionEvent> {

    private TextField email;
    private TextField password;
    private UserServiceRestClient userServiceRestClient;
    private ReservationServiceRestClient reservationServiceRestClient;

    public LoginController(TextField email, TextField password) {
        this.email = email;
        this.password = password;
        this.userServiceRestClient = new UserServiceRestClient();
        this.reservationServiceRestClient = new ReservationServiceRestClient();
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            String token = userServiceRestClient.login(email.getText(), password.getText());
            ClientApp.getInstance().setToken(token);
            User user = userServiceRestClient.getUser(token);
            if (user != null && user.isConfirm()) {
                if(user.getRole().equals("client")) {
                    Scene sc = new Scene(new ReservationsView(), 800, 400);
                    Main.mainStage.setScene(sc);
                    Main.mainStage.show();
                    Main.mainStage.setTitle("RentWheels");
                } else if (user.getRole().equals("manager")) {
                    Scene sc = new Scene(new ManagerView(null), 800, 400);
                    Main.mainStage.setScene(sc);
                    Main.mainStage.show();
                    Main.mainStage.setTitle("RentWheels");
                } else if (user.getRole().equals("admin")) {
                    Scene sc = new Scene(new AdminView(), 800, 400);
                    Main.mainStage.setScene(sc);
                    Main.mainStage.show();
                    Main.mainStage.setTitle("RentWheels");
                }
            } else if(user != null) {
                Scene sc = null;
                if(user.getRole().equals("client"))
                    sc = new Scene(new ActivationView("CLIENT", user.getId()), 400, 200);
                else if (user.getRole().equals("manager")) {
                    ManagerCreateDto managerCreateDto = new ManagerCreateDto();
//                    managerCreateDto.setFirstName();
//                    managerCreateDto.setLastName();
//                    managerCreateDto.setUsername();
//                    managerCreateDto.setEmail();
                    sc = new Scene(new ActivationView("MANAGER", user.getId()), 400, 200);
                }
                Main.activationStage.setScene(sc);
                Main.activationStage.show();
                Main.activationStage.setTitle("Activation");
            } else {
//              TODO: write label if user is not found
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
