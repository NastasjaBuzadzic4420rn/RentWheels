package gui.view;

import gui.controller.CalculatePriceController;
import gui.controller.CreateReservationController;
import gui.epoch.EpochConverter;
import gui.model.Vehicle;
import gui.restclient.ReservationServiceRestClient;
import gui.restclient.dto.ReservationDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.time.LocalDate;

public class DetailVehicleView extends VBox {

    private Vehicle vehicle;
    private ObservableList<ReservationDto> reservations;
    private HBox topBox;
    private VBox infoBox;
    private Label manufacturerLabel;
    private Label companyLabel;
    private Label priceLabel;
    private Label priceWithDiscountLabel;
    private Label typeLabel;
    private VBox unavailableBox;
    private Label unavailableLabel;
    private HBox datesBox;
    private VBox startBox;
    private VBox endBox;
    private TitledPane reservePane;
    private VBox reserveBox;
    private HBox rentDateBox;
    private DatePicker startDateField;
    private DatePicker endDateField;
    private Label availabilityLabel;
    private Button rentButton;
    private ReservationServiceRestClient reservationServiceRestClient;
    private EpochConverter epochConverter;

    public DetailVehicleView(Vehicle vehicle) {
        epochConverter = new EpochConverter();


        this.vehicle = vehicle;
        setSpacing(20);
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10));

        initUnavailablePeriods();
        initViewElements();
        addViewElements();
        initListener();
    }

    private void initUnavailablePeriods(){
        reservationServiceRestClient = new ReservationServiceRestClient();
        try {
            reservations = FXCollections.observableArrayList(reservationServiceRestClient.getReservations(vehicle.getCompanyVehicleDto().getId()));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void initViewElements() {
        topBox = new HBox();
        topBox.setSpacing(10);

        infoBox = new VBox();

        manufacturerLabel = new Label(vehicle.getManufacturer() + " " + vehicle.getModel());
        manufacturerLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        typeLabel = new Label(vehicle.getType());
        typeLabel.setStyle("-fx-font-size: 16px;");

        companyLabel = new Label("Rental Company: " + vehicle.getCompany());
        companyLabel.setStyle("-fx-font-size: 13px;");

        priceLabel = new Label("$" + vehicle.getPricePerDay() + "  per day ");
        priceLabel.setStyle("-fx-font-size: 16px;");

        priceWithDiscountLabel = new Label("$" + (new CalculatePriceController()).calculatePrice(vehicle.getPricePerDay()) + "  per day ");
        priceWithDiscountLabel.setStyle("-fx-font-size: 18px;");
        priceWithDiscountLabel.setTextFill(Color.RED);


        unavailableBox = new VBox();

        unavailableLabel = new Label("Unavailable periods:");
        unavailableLabel.setStyle("-fx-font-size: 16px;");

        datesBox = new HBox();
        datesBox.setSpacing(5);

        startBox = new VBox();
        endBox = new VBox();


        reservePane = new TitledPane();
        reservePane.setText("Check availability");
        reservePane.setCollapsible(false);

        reserveBox = new VBox();
        reserveBox.setSpacing(10);
        reserveBox.setPadding(new Insets(10));
        reserveBox.setAlignment(Pos.CENTER);

        rentDateBox = new HBox();
        rentDateBox.setSpacing(10);
        rentDateBox.setPadding(new Insets(10));

        startDateField = new DatePicker();
        startDateField.setPromptText("Start Date");
        endDateField = new DatePicker();
        endDateField.setPromptText("End Date");

        availabilityLabel = new Label("Select your dates");

        rentButton = new Button("Rent");
        rentButton.setOnAction(new CreateReservationController(this));
    }

    private void conformation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Create reservation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to rent this vehicle");
        alert.initOwner(getScene().getWindow());
        alert.showAndWait();
    }

    private void addViewElements() {
        infoBox.getChildren().addAll(manufacturerLabel, typeLabel, companyLabel, priceLabel, priceWithDiscountLabel);
        for(ReservationDto reservation : reservations){
            startBox.getChildren().add(new Label("from " + epochConverter.toLocalDate(reservation.getStartDate())));
            endBox.getChildren().add(new Label("to " + epochConverter.toLocalDate(reservation.getEndDate())));
        }
        datesBox.getChildren().addAll(startBox, endBox);
        unavailableBox.getChildren().addAll(unavailableLabel, datesBox);
        topBox.getChildren().addAll(infoBox, unavailableBox);

        rentDateBox.getChildren().addAll(startDateField, endDateField);
        reserveBox.getChildren().addAll(rentDateBox, availabilityLabel);
        reservePane.setContent(reserveBox);

        getChildren().addAll(topBox, reservePane);
    }

    private void initListener(){
        endDateField.setOnAction(e -> {
            if(startDateField.getValue() == null)
                availabilityLabel.setText("Select your dates");

            if(startDateField.getValue() != null
                    && (!startDateField.getValue().isBefore(endDateField.getValue()) || startDateField.getValue().isBefore(LocalDate.now())))
                availabilityLabel.setText("Invalid input of dates.");

            if(startDateField.getValue() != null && startDateField.getValue().isBefore(endDateField.getValue())){
                if(isAvailable() && !reserveBox.getChildren().contains(rentButton)) {
                    reserveBox.getChildren().add(rentButton);
                    availabilityLabel.setText("Vehicle is available during this period.");
                }else {
                    reserveBox.getChildren().remove(rentButton);
                    availabilityLabel.setText("Vehicle is not available during this period.");
                }
            }
        });

        startDateField.setOnAction(e -> {
            if(endDateField.getValue() == null)
                availabilityLabel.setText("Select your dates");

            if(endDateField.getValue() != null
                    && (!endDateField.getValue().isAfter(startDateField.getValue())) || startDateField.getValue().isBefore(LocalDate.now()))
                availabilityLabel.setText("Invalid input of dates");

            if(endDateField.getValue() != null && endDateField.getValue().isAfter(startDateField.getValue())){
                if(isAvailable() && !reserveBox.getChildren().contains(rentButton)) {
                    reserveBox.getChildren().add(rentButton);
                    availabilityLabel.setText("Vehicle is available during this period.");
                } else if(reserveBox.getChildren().contains(rentButton)){
                        reserveBox.getChildren().remove(rentButton);
                        availabilityLabel.setText("Vehicle is not available during this period.");
                }
            }
        });
    }




    private boolean isAvailable(){
        for(ReservationDto reservation : reservations){
            if((startDateField.getValue().isAfter(epochConverter.toLocalDate(reservation.getStartDate()))
                    && startDateField.getValue().isBefore(epochConverter.toLocalDate(reservation.getEndDate())))
            || (endDateField.getValue().isAfter(epochConverter.toLocalDate(reservation.getStartDate()))
                    && endDateField.getValue().isBefore(epochConverter.toLocalDate(reservation.getEndDate())))
            || (startDateField.getValue().isBefore(epochConverter.toLocalDate(reservation.getStartDate()))
                    && endDateField.getValue().isAfter(epochConverter.toLocalDate(reservation.getEndDate())))){
                return false;
            }
        }
        return true;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public DatePicker getStartDateField() {
        return startDateField;
    }

    public DatePicker getEndDateField() {
        return endDateField;
    }
}
