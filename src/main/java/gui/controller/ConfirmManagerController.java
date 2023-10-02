package gui.controller;

import gui.ClientApp;
import gui.Main;
import gui.model.User;
import gui.restclient.ReservationServiceRestClient;
import gui.restclient.UserServiceRestClient;
import gui.restclient.dto.*;
import gui.view.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import java.io.IOException;

public class ConfirmManagerController implements EventHandler<ActionEvent> {

    private ActivationView activationView;

    private UserServiceRestClient userServiceRestClient;
    private ReservationServiceRestClient reservationServiceRestClient;

    public ConfirmManagerController(ActivationView activationView) {
        this.activationView = activationView;
        this.userServiceRestClient = new UserServiceRestClient();
        this.reservationServiceRestClient = new ReservationServiceRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        try {
            ActivationDto activationDto = new ActivationDto();
            activationDto.setUserId(activationView.getUserId());
            activationDto.setActivationCode(activationView.getActivationField().getText());

            ManagerDto managerDto = userServiceRestClient.confirmManager(activationDto);
            if(managerDto.isConfirmed()){
                activationView.getLblWarning().setVisible(false);
                Main.activationStage.close();

                String token = userServiceRestClient.login(managerDto.getEmail(), managerDto.getPassword());
                ClientApp.getInstance().setToken(token);

                Scene sc = new Scene(new RegisterCompanyView(), 400, 400);
                Main.mainStage.setScene(sc);
                Main.mainStage.show();
                Main.mainStage.setTitle("RentWheels");
            } else {
                activationView.getLblWarning().setVisible(true);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
