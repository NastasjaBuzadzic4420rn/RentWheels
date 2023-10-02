package gui.view;

import gui.Main;
import gui.controller.FilterVehiclesController;
import gui.controller.LoadAllVehiclesController;
import gui.model.Vehicle;
import gui.restclient.ReservationServiceRestClient;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class SearchView extends VBox {

    private Label titleLabel;
    private Button reservationButton;
    private HBox topButtonsBox;
    private TitledPane filtersPane;
    private VBox filtersBox;
    private TextField cityField;
    private TextField companyField;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private Button filterButton;
    private Button clearFilterButton;
    private HBox bottomButtonsBox;
    private TableView<Vehicle> tableView;
    private Button detailButton;
    private ObservableList<Vehicle> vehicles;
    private ReservationServiceRestClient reservationServiceRestClient;

    public SearchView() {
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10));

        initVehicles();
        initViewElements();
        addViewElements();
    }

    private void initVehicles(){
        try {
            reservationServiceRestClient = new ReservationServiceRestClient();
            vehicles = FXCollections.observableArrayList(reservationServiceRestClient.getAllVehicles());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void initViewElements() {
        titleLabel = new Label("Rent a Car");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        topButtonsBox = new HBox();
        topButtonsBox.setAlignment(Pos.BASELINE_RIGHT);
        reservationButton = new Button("My reservations");
        reservationButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        reservationButton.setOnAction(e -> openReservations());
        topButtonsBox.getChildren().add(reservationButton);

        filtersPane = new TitledPane();
        filtersPane.setText("Filters");
        filtersPane.setCollapsible(true);

        filtersBox = new VBox();
        filtersBox.setSpacing(10);
        filtersBox.setPadding(new Insets(10));

        cityField = new TextField();
        cityField.setPromptText("City");
        companyField = new TextField();
        companyField.setPromptText("Company");
        startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Start Date");
        endDatePicker = new DatePicker();
        endDatePicker.setPromptText("End Date");
        filterButton = new Button("Filter");
        filterButton.setAlignment(Pos.BOTTOM_RIGHT);
        filterButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        filterButton.setOnAction(new FilterVehiclesController(this));

        bottomButtonsBox = new HBox();
        bottomButtonsBox.setAlignment(Pos.BASELINE_RIGHT);
        bottomButtonsBox.setSpacing(5);

        clearFilterButton = new Button("Clear filters");
        clearFilterButton.setOnAction(new LoadAllVehiclesController(this));


        bottomButtonsBox.getChildren().add(clearFilterButton);

        tableView = new TableView<>();
        addFields(tableView);
        tableView.setItems(vehicles);

        detailButton = new Button("See details");
        detailButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        detailButton.setOnAction(e -> openDetails());
    }

    private void addViewElements() {
        getChildren().addAll(titleLabel, topButtonsBox, filtersPane, bottomButtonsBox, tableView, detailButton);

        filtersPane.setContent(filtersBox);

        filtersBox.getChildren().addAll(cityField, companyField, startDatePicker, endDatePicker, filterButton);
    }

    private void openReservations(){
        Scene sc = new Scene(new ReservationsView(), 800, 400);
        Main.mainStage.setScene(sc);
        Main.mainStage.show();
        Main.mainStage.setTitle("RentWheels");
    }

    private void addFields(TableView<Vehicle> tableView){
        TableColumn<Vehicle, String> manufacturerColumn = new TableColumn<>("Manufacturer");
        manufacturerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getManufacturer()));

        TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));

        TableColumn<Vehicle, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));

        TableColumn<Vehicle, String> companyColumn = new TableColumn<>("Company");
        companyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCompany()));

        TableColumn<Vehicle, Double> priceColumn = new TableColumn<>("Price per day");
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPricePerDay()).asObject());

        tableView.getColumns().addAll(manufacturerColumn, modelColumn, typeColumn, companyColumn, priceColumn);
    }

    private void openDetails(){
        Vehicle selectedVehicle = tableView.getSelectionModel().getSelectedItem();
        Scene sc = new Scene(new DetailVehicleView(selectedVehicle), 450, 350);
        Main.detailVehicleStage.setScene(sc);
        Main.detailVehicleStage.show();
        Main.detailVehicleStage.setTitle(selectedVehicle.getManufacturer() + " " + selectedVehicle.getModel());
    }


    public TextField getCityField() {
        return cityField;
    }

    public TextField getCompanyField() {
        return companyField;
    }

    public DatePicker getStartDatePicker() {
        return startDatePicker;
    }

    public DatePicker getEndDatePicker() {
        return endDatePicker;
    }

    public TableView<Vehicle> getTableView() {
        return tableView;
    }
}
