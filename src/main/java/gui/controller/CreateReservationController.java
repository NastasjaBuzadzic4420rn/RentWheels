package gui.controller;

import gui.ClientApp;
import gui.Main;
import gui.epoch.EpochConverter;
import gui.restclient.ReservationServiceRestClient;
import gui.restclient.UserServiceRestClient;
import gui.restclient.dto.CreateReservationDto;
import gui.restclient.dto.UserStatusDto;
import gui.view.DetailVehicleView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

import java.io.IOException;

public class CreateReservationController implements EventHandler<ActionEvent> {

    private DetailVehicleView detailVehicleView;
    private ReservationServiceRestClient reservationServiceRestClient;
    private UserServiceRestClient userServiceRestClient;
    private EpochConverter epochConverter;

    public CreateReservationController(DetailVehicleView detailVehicleView) {
        this.detailVehicleView = detailVehicleView;
        this.reservationServiceRestClient = new ReservationServiceRestClient();
        this.userServiceRestClient = new UserServiceRestClient();
        this.epochConverter = new EpochConverter();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation of reservation");
            alert.setHeaderText("Confirm your reservation");
            alert.setContentText("Are you sure you want to rent this vehicle in a period from " + detailVehicleView.getStartDateField().getValue()
             + " to " + detailVehicleView.getEndDateField().getValue()+ "?");

            alert.showAndWait().ifPresent(response -> {
                if (response == javafx.scene.control.ButtonType.OK) {
                    try {

                        UserStatusDto status = userServiceRestClient.getUserStatus();

                        CreateReservationDto createReservationDto = new CreateReservationDto();
                        createReservationDto.setUserId(reservationServiceRestClient.getUser(ClientApp.getInstance().getToken()).getId());
                        createReservationDto.setCompanyVehicleId(detailVehicleView.getVehicle().getCompanyVehicleDto().getId());
                        createReservationDto.setPriceWithDiscount(detailVehicleView.getVehicle().getPricePerDay() * status.getDiscount());
                        createReservationDto.setStartDate(epochConverter.toLong(detailVehicleView.getStartDateField().getValue()));
                        createReservationDto.setEndDate(epochConverter.toLong(detailVehicleView.getEndDateField().getValue()));
                        reservationServiceRestClient.saveReservation(createReservationDto);

                        Main.detailVehicleStage.close();
                    } catch (IOException e) {
                            throw new RuntimeException(e);
                    }
                }
            });

    }
}
