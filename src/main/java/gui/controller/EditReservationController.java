package gui.controller;

import gui.epoch.EpochConverter;
import gui.model.Reservation;
import gui.restclient.ReservationServiceRestClient;
import gui.restclient.dto.EditReservationDto;
import gui.view.ReservationsView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class EditReservationController implements EventHandler<ActionEvent> {

    private ReservationsView reservationsView;
    private ReservationServiceRestClient reservationServiceRestClient;
    private EpochConverter epochConverter;

    public EditReservationController(ReservationsView reservationsView) {
        this.reservationsView = reservationsView;
        this.reservationServiceRestClient = new ReservationServiceRestClient();
        this.epochConverter = new EpochConverter();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Reservation selectedItem = reservationsView.getTableView().getSelectionModel().getSelectedItem();
        if(selectedItem != null && selectedItem.getReservationDto().isActive()) {
            editSelectedReservation(selectedItem);
            reservationsView.getLoadReservationsController().loadData();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Edit reservation");
            alert.setHeaderText(null);
            alert.setContentText("Selected reservation is canceled and cannot be changed.");
            alert.initOwner(reservationsView.getScene().getWindow());
            alert.showAndWait();
        }
    }

    private void editSelectedReservation(Reservation reservation){
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Edit Reservation");

        DialogPane dialogPane = new DialogPane();
        dialogPane.setPadding(new Insets(10));

        VBox mainBox = new VBox();
        mainBox.setSpacing(10);
        HBox startDateBox = new HBox();
        startDateBox.setSpacing(10);
        HBox endDateBox = new HBox();
        endDateBox.setSpacing(10);

        Label startDateLabel = new Label("Start date: ");
        Label endDateLabel = new Label("End date: ");

        DatePicker startDateField = new DatePicker();
        startDateField.setValue(epochConverter.toLocalDate(reservation.getStartDate()));
        DatePicker endDateField = new DatePicker();
        endDateField.setValue(epochConverter.toLocalDate(reservation.getEndDate()));

        startDateBox.getChildren().addAll(startDateLabel, startDateField);
        endDateBox.getChildren().addAll(endDateLabel, endDateField);

        mainBox.getChildren().addAll(startDateBox, endDateBox);

        dialogPane.setContent(mainBox);

        dialog.setDialogPane(dialogPane);

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                EditReservationDto editReservationDto = new EditReservationDto();
                editReservationDto.setId(reservation.getReservationDto().getId());
                editReservationDto.setStartDate(epochConverter.toLong(startDateField.getValue()));
                editReservationDto.setEndDate(epochConverter.toLong(endDateField.getValue()));
                try {
                    reservationServiceRestClient.editReservationDto(editReservationDto);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return null;
        });

        dialog.initOwner(reservationsView.getScene().getWindow());
        dialog.showAndWait();
    }


}
