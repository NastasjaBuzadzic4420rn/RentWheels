package gui.controller;

import gui.restclient.ReservationServiceRestClient;
import gui.view.ReservationsView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;

public class LoadReservationsController{

    private ReservationsView reservationsView;
    private ReservationServiceRestClient reservationServiceRestClient;

    public LoadReservationsController(ReservationsView reservationsView) {
        this.reservationsView = reservationsView;
        this.reservationServiceRestClient = new ReservationServiceRestClient();
    }

    public void loadData() {
        try {
            reservationsView.setAllReservations(FXCollections.observableArrayList(reservationServiceRestClient.getAllReservationsFromUser()));
            reservationsView.setActiveReservations(FXCollections.observableArrayList(reservationServiceRestClient.getActiveReservationsFromUser()));
            if(reservationsView.isONLY_ACTIVE())
                reservationsView.getTableView().setItems(reservationsView.getActiveReservations());
            else
                reservationsView.getTableView().setItems(reservationsView.getAllReservations());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
