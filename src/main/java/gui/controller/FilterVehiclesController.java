package gui.controller;

import gui.epoch.EpochConverter;
import gui.model.Vehicle;
import gui.restclient.ReservationServiceRestClient;
import gui.restclient.dto.CompanyVehicleDto;
import gui.restclient.dto.FilterDto;
import gui.restclient.dto.VehicleDto;
import gui.restclient.dto.VehicleTypeDto;
import gui.view.SearchView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterVehiclesController implements EventHandler<ActionEvent> {

    private SearchView searchView;
    private ReservationServiceRestClient reservationServiceRestClient;
    private EpochConverter epochConverter;

    public FilterVehiclesController(SearchView searchView) {
        this.searchView = searchView;
        this.reservationServiceRestClient = new ReservationServiceRestClient();
        this.epochConverter = new EpochConverter();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        FilterDto filterDto = new FilterDto();
        filterDto.setCity(searchView.getCityField().getText());
        filterDto.setCompany(searchView.getCompanyField().getText());
        if(searchView.getStartDatePicker().getValue() != null)
            filterDto.setStartDate(epochConverter.toLong(searchView.getStartDatePicker().getValue()));
        if(searchView.getEndDatePicker().getValue() != null)
            filterDto.setEndDate(epochConverter.toLong(searchView.getEndDatePicker().getValue()));
        try {
            List<Vehicle> vehicles = reservationServiceRestClient.filterVehicles(filterDto).stream()
                    .map(reservationServiceRestClient::companyVehicleToVehicle)
                    .collect(Collectors.toList());

            searchView.getTableView().setItems(FXCollections.observableArrayList(vehicles));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
