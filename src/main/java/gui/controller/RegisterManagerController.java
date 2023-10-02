package gui.controller;

import gui.ClientApp;
import gui.Main;
import gui.epoch.EpochConverter;
import gui.restclient.ReservationServiceRestClient;
import gui.restclient.UserServiceRestClient;
import gui.restclient.dto.*;
import gui.view.ActivationView;
import gui.view.RegisterManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import okhttp3.Connection;

import java.io.IOException;

public class RegisterManagerController  implements EventHandler<ActionEvent> {
    private RegisterManagerView view;
    private UserServiceRestClient userServiceRestClient;
    private EpochConverter epochConverter;

    public RegisterManagerController(RegisterManagerView view) {
        this.view = view;
        this.userServiceRestClient = new UserServiceRestClient();
        this.epochConverter = new EpochConverter();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (allFieldsAreFilled()) {
            view.getLblWarning().setVisible(false);
            try {
                ManagerCreateDto managerCreateDto = new ManagerCreateDto();
                managerCreateDto.setFirstName(view.getTfFirstName().getText());
                managerCreateDto.setLastName(view.getTfLastName().getText());
                managerCreateDto.setEmail(view.getTfEmail().getText());
                managerCreateDto.setUsername(view.getTfUsername().getText());
                managerCreateDto.setPassword(view.getTfPassword().getText());
                managerCreateDto.setBirthDate(epochConverter.toLong(view.getDpBirthDate().getValue()));

                userServiceRestClient.registerManager(managerCreateDto);

                ManagerDto managerDto = userServiceRestClient.getManagerByUsername(managerCreateDto.getUsername());

                Scene sc = new Scene(new ActivationView("MANAGER", managerDto.getId()), 400, 200);
                Main.activationStage.setScene(sc);
                Main.activationStage.show();
                Main.activationStage.setTitle("Activation");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            view.getLblWarning().setVisible(true);
        }
    }

    private boolean allFieldsAreFilled(){
        return view.getTfFirstName().getText() != null &&
                view.getTfLastName().getText() != null &&
                view.getTfEmail().getText() != null &&
                view.getTfUsername().getText() != null &&
                view.getTfPassword().getText() != null &&
                view.getDpBirthDate().getValue() != null;
    }


}
