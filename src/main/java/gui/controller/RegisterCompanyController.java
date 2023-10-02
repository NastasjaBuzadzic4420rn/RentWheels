package gui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import gui.ClientApp;
import gui.Main;
import gui.model.User;
import gui.restclient.ReservationServiceRestClient;
import gui.restclient.UserServiceRestClient;
import gui.restclient.dto.*;
import gui.view.ManagerView;
import gui.view.RegisterCompanyView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import java.io.IOException;

public class RegisterCompanyController implements EventHandler<ActionEvent> {

    private RegisterCompanyView view;
    private ReservationServiceRestClient reservationServiceRestClient;
    private UserServiceRestClient userServiceRestClient;

    public RegisterCompanyController(RegisterCompanyView registerCompanyView) {
        this.view = registerCompanyView;
        this.reservationServiceRestClient = new ReservationServiceRestClient();
        this.userServiceRestClient = new UserServiceRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        try {
            if(allFieldsAreFilled()) {
                view.getLblWarning().setVisible(false);
                CompanyManagerDto companyManagerDto = new CompanyManagerDto();
                companyManagerDto.setName(view.getCompanyNameField().getText());
                companyManagerDto.setDescription(view.getDescriptionField().getText());
                companyManagerDto.setCity(view.getCityField().getText());

                User user = userServiceRestClient.getUser(ClientApp.getInstance().getToken());
                companyManagerDto.setManagerId(user.getId());
                ManagerDto managerDto = userServiceRestClient.getManager(user.getId());

                companyManagerDto.setFirstName(managerDto.getFirstName());
                companyManagerDto.setLastName(managerDto.getLastName());
                companyManagerDto.setUsername(managerDto.getUsername());
                companyManagerDto.setEmail(managerDto.getEmail());

                CompanyDto companyDto = reservationServiceRestClient.addCompany(companyManagerDto);

                Scene sc = new Scene(new ManagerView(companyDto), 400, 400);
                Main.mainStage.setScene(sc);
                Main.mainStage.show();
                Main.mainStage.setTitle("RentWheels");

            } else {
                view.getLblWarning().setVisible(true);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private boolean allFieldsAreFilled(){
        return view.getCompanyNameField().getText() != null &&
                view.getDescriptionField().getText() != null &&
                view.getCityField().getText() != null;
    }
}
