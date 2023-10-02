package gui.controller;

import gui.model.Vehicle;
import gui.restclient.ReservationServiceRestClient;
import gui.view.SearchView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.util.List;

public class LoadAllVehiclesController implements EventHandler<ActionEvent> {

    private SearchView searchView;
    private ReservationServiceRestClient reservationServiceRestClient;

    public LoadAllVehiclesController(SearchView searchView) {
        this.searchView = searchView;
        this.reservationServiceRestClient = new ReservationServiceRestClient();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            List<Vehicle> vehicles = reservationServiceRestClient.getAllVehicles();
            searchView.getTableView().setItems(FXCollections.observableArrayList(vehicles));

            searchView.getCityField().setText("");
            searchView.getCompanyField().setText("");
            searchView.getStartDatePicker().setValue(null);
            searchView.getEndDatePicker().setValue(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
