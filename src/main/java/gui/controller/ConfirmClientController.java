package gui.controller;

import gui.ClientApp;
import gui.Main;
import gui.restclient.UserServiceRestClient;
import gui.restclient.dto.ActivationDto;
import gui.restclient.dto.ClientDto;
import gui.view.ActivationView;
import gui.view.ReservationsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import java.io.IOException;

public class ConfirmClientController implements EventHandler<ActionEvent> {
    private ActivationView activationView;
    private UserServiceRestClient userServiceRestClient;

    public ConfirmClientController(ActivationView activationView) {
        this.activationView = activationView;
        this.userServiceRestClient = new UserServiceRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        try {
            ActivationDto activationDto = new ActivationDto();
            activationDto.setUserId(activationView.getUserId());
            activationDto.setActivationCode(activationView.getActivationField().getText());

            ClientDto clientDto = userServiceRestClient.confirmClient(activationDto);
            if(clientDto.isConfirmed()){
                activationView.getLblWarning().setVisible(false);
                Main.activationStage.close();

                String token = userServiceRestClient.login(clientDto.getEmail(), clientDto.getPassword());
                ClientApp.getInstance().setToken(token);
                Scene sc = new Scene(new ReservationsView(), 800, 400);
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
