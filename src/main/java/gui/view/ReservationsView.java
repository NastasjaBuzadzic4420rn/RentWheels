package gui.view;

import gui.Main;
import gui.controller.CancelReservationController;
import gui.controller.EditReservationController;
import gui.controller.LoadReservationsController;
import gui.epoch.EpochConverter;
import gui.model.Reservation;
import gui.restclient.ReservationServiceRestClient;
import gui.restclient.dto.EditReservationDto;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

import java.io.IOException;
import java.time.*;

public class ReservationsView extends VBox {

    private TableView<Reservation> tableView;
    private ObservableList<Reservation> allReservations;
    private ObservableList<Reservation> activeReservations;
    private Label titleLabel;
    private Button rentButton;
    private Button changeButton;
    private HBox topButtons;
    private Button deleteButton;
    private Button editButton;
    private HBox bottomButtons;
    private EpochConverter epochConverter;
    private LoadReservationsController loadReservationsController;

    private boolean ONLY_ACTIVE = true;

    public ReservationsView() {
        epochConverter = new EpochConverter();

        setSpacing(10);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10));

        initViewElements();
        initListeners();
        loadData();
        addViewElements();
    }

    private void loadData(){
        loadReservationsController = new LoadReservationsController(this);
        loadReservationsController.loadData();
    }

    private void initViewElements(){
        titleLabel = new Label("My Reservations");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        rentButton = new Button("Rent a Car");
        rentButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        changeButton = new Button("All reservations");
        changeButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");


        topButtons = new HBox();
        topButtons.setAlignment(Pos.BASELINE_RIGHT);
        topButtons.setPadding(new Insets(10));
        topButtons.setSpacing(10);

        tableView = new TableView<>();
        tableView.setItems(activeReservations);

        TableColumn<Reservation, String> companyColumn = new TableColumn<>("Company");
        companyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCompany()));

        TableColumn<Reservation, String> manufacturerColumn = new TableColumn<>("Manufacturer");
        manufacturerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getManufacturer()));

        TableColumn<Reservation, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));

        TableColumn<Reservation, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));

        TableColumn<Reservation, String> startDateColumn = new TableColumn<>("Start date");
        startDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(epochConverter.toLocalDate(cellData.getValue().getStartDate()).toString()));

        TableColumn<Reservation, String> endDateColumn = new TableColumn<>("End date");
        endDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(epochConverter.toLocalDate(cellData.getValue().getEndDate()).toString()));

        TableColumn<Reservation, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPriceWithDiscount()).asObject());

        tableView.getColumns().addAll(companyColumn, manufacturerColumn, modelColumn, typeColumn, startDateColumn, endDateColumn, priceColumn);

        deleteButton = new Button("Cancel");
        deleteButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        editButton = new Button("Edit");
        editButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        bottomButtons = new HBox();
        bottomButtons.setAlignment(Pos.CENTER);
        bottomButtons.setPadding(new Insets(10));
        bottomButtons.setSpacing(10);
    }

    private void initListeners(){
        rentButton.setOnAction(e -> openRentCarView());
        changeButton.setOnAction(e -> {
            try {
                changeTable();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        deleteButton.setOnAction(new CancelReservationController(this));
        editButton.setOnAction(new EditReservationController(this));

    }

    private void addViewElements(){
        topButtons.getChildren().addAll(rentButton, changeButton);
        bottomButtons.getChildren().addAll(deleteButton, editButton);
        getChildren().addAll(titleLabel, topButtons, tableView, bottomButtons);
    }

    private void openRentCarView() {
        Scene sc = new Scene(new SearchView(), 800, 800);
        Main.mainStage.setScene(sc);
        Main.mainStage.show();
        Main.mainStage.setTitle("RentWheels");
    }

    private void changeTable() throws IOException {
        if (ONLY_ACTIVE){
            changeButton.setText("Active reservations");
            tableView.setItems(allReservations);
            ONLY_ACTIVE = false;
        } else {
            changeButton.setText("All reservations");
            tableView.setItems(activeReservations);
            ONLY_ACTIVE = true;
        }
    }

    public TableView<Reservation> getTableView() {
        return tableView;
    }

    public ObservableList<Reservation> getAllReservations() {
        return allReservations;
    }

    public void setAllReservations(ObservableList<Reservation> allReservations) {
        this.allReservations = allReservations;
    }

    public ObservableList<Reservation> getActiveReservations() {
        return activeReservations;
    }

    public void setActiveReservations(ObservableList<Reservation> activeReservations) {
        this.activeReservations = activeReservations;
    }

    public boolean isONLY_ACTIVE() {
        return ONLY_ACTIVE;
    }

    public LoadReservationsController getLoadReservationsController() {
        return loadReservationsController;
    }
}
