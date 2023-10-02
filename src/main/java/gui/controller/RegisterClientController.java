package gui.controller;

import gui.ClientApp;
import gui.Main;
import gui.restclient.dto.ClientCreateDto;
import gui.restclient.dto.ClientDto;
import gui.view.ActivationView;
import gui.view.ReservationsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import gui.restclient.UserServiceRestClient;
import gui.view.RegisterClientView;
import javafx.scene.Scene;

import java.io.IOException;

public class RegisterClientController implements EventHandler<ActionEvent> {

    private RegisterClientView registerClientView;
    private UserServiceRestClient userServiceRestClient;

    public RegisterClientController(RegisterClientView registerClientView){
        this.registerClientView = registerClientView;
        this.userServiceRestClient = new UserServiceRestClient();
    }
    @Override
    public void handle(ActionEvent event) {
        if(allFieldsAreFilled()) {
            registerClientView.getLblWarning().setVisible(false);
            ClientCreateDto clientCreateDto = new ClientCreateDto();
            clientCreateDto.setFirstName(registerClientView.getTfFirstName().getText());
            clientCreateDto.setLastName(registerClientView.getTfLastName().getText());
            clientCreateDto.setEmail(registerClientView.getTfEmail().getText());
            clientCreateDto.setUsername(registerClientView.getTfUsername().getText());
            clientCreateDto.setPassword(registerClientView.getTfPassword().getText());

            ClientDto client = null;
            try {
                client = userServiceRestClient.registerClient(clientCreateDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene sc = new Scene(new ActivationView("CLIENT", client.getId()), 400, 200);
            Main.activationStage.setScene(sc);
            Main.activationStage.show();
            Main.activationStage.setTitle("Activation");
        } else {
            registerClientView.getLblWarning().setVisible(true);
        }

    }

    private boolean allFieldsAreFilled(){
        return registerClientView.getTfFirstName().getText() != null &&
                registerClientView.getTfLastName().getText() != null &&
                registerClientView.getTfEmail().getText() != null &&
                registerClientView.getTfUsername().getText() != null &&
                registerClientView.getTfPassword().getText() != null;
    }
}
