package gui.controller;

import gui.ClientApp;
import gui.Main;
import gui.model.Reservation;
import gui.restclient.ReservationServiceRestClient;
import gui.restclient.UserServiceRestClient;
import gui.restclient.dto.CreateReservationDto;
import gui.view.ReservationsView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

import java.io.IOException;

public class CancelReservationController implements EventHandler<ActionEvent> {

    private ReservationsView reservationsView;
    private ReservationServiceRestClient reservationServiceRestClient;
    private UserServiceRestClient userServiceRestClient;

    public CancelReservationController(ReservationsView reservationsView) {
        this.reservationsView = reservationsView;
        this.reservationServiceRestClient = new ReservationServiceRestClient();
        this.userServiceRestClient = new UserServiceRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Reservation selectedItem = reservationsView.getTableView().getSelectionModel().getSelectedItem();
        if(selectedItem != null && selectedItem.getReservationDto().isActive()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation of reservation");
            alert.setHeaderText("Confirm your reservation");
            alert.setContentText("Are you sure you want to cancel this reservation?");

            alert.showAndWait().ifPresent(response -> {
                if (response == javafx.scene.control.ButtonType.OK) {
                    try {
                        reservationServiceRestClient.cancelReservation(selectedItem.getReservationDto());
                        reservationsView.getLoadReservationsController().loadData();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cancel reservation");
            alert.setHeaderText(null);
            alert.setContentText("Selected reservation is already canceled.");
            alert.initOwner(reservationsView.getScene().getWindow());
            alert.showAndWait();
        }
    }

}
