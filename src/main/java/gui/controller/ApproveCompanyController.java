package gui.controller;

import gui.epoch.EpochConverter;
import gui.model.Company;
import gui.restclient.ReservationServiceRestClient;
import gui.restclient.dto.CompanyManagerDto;
import gui.view.AdminView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;

public class ApproveCompanyController implements EventHandler<ActionEvent> {
    private AdminView adminView;
    private ReservationServiceRestClient reservationServiceRestClient;
    private EpochConverter epochConverter;

    public ApproveCompanyController(AdminView adminView) {
        this.adminView = adminView;
        this.reservationServiceRestClient = new ReservationServiceRestClient();
        epochConverter = new EpochConverter();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Company selectedCompany = adminView.getTableView().getSelectionModel().getSelectedItem();
        if(selectedCompany != null && !selectedCompany.isApproved()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Review information");
            alert.setHeaderText("Company information");

//            alert.setContentText(
//                    "Company Name: " + selectedCompany.getName() + "\n" +
//                            "Description:\n " + selectedCompany.getDescription() + "\n" +
//                            "City: " + selectedCompany.getCity() + "\n\n" +
//                            "<b>Manager Information:</b>\\n" +
//                            "Username: " + selectedCompany.getManager().getUsername() + "\n" +
//                            "Email: " + selectedCompany.getManager().getEmail() + "\n" +
//                            "Birth Date: " + epochConverter.toLocalDate(selectedCompany.getManager().getBirthDate()).toString() + "\n" +
//                            "First Name: " + selectedCompany.getManager().getFirstName() + "\n" +
//                            "Last Name: " + selectedCompany.getManager().getLastName() + "\n" +
//                            "Started Working: " + epochConverter.toLocalDate(selectedCompany.getManager().getStartedWorking()).toString() + "\n\n" +
//                            "Do you want to confirm the approval?"
//            );
            alert.getDialogPane().setContent(makeContent(selectedCompany));

            alert.showAndWait().ifPresent(response -> {
                if (response == javafx.scene.control.ButtonType.OK) {
                    try {
                        CompanyManagerDto companyManagerDto = new CompanyManagerDto();
                        companyManagerDto.setName(selectedCompany.getName());
                        companyManagerDto.setDescription(selectedCompany.getDescription());
                        companyManagerDto.setCity(selectedCompany.getCity());

                        companyManagerDto.setFirstName(selectedCompany.getManager().getFirstName());
                        companyManagerDto.setLastName(selectedCompany.getManager().getLastName());
                        companyManagerDto.setUsername(selectedCompany.getManager().getUsername());
                        companyManagerDto.setEmail(selectedCompany.getManager().getEmail());

                        reservationServiceRestClient.approveCompany(selectedCompany.getId(), companyManagerDto);
                        adminView.getLoadCompaniesController().loadData();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Approve company");
            alert.setHeaderText(null);
            alert.setContentText("Selected company is already approved.");
            alert.initOwner(adminView.getScene().getWindow());
            alert.showAndWait();
        }
    }

    private GridPane makeContent(Company company){
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        gridPane.addRow(0, new Text("Company Name:"), new Text(company.getName()));
        gridPane.addRow(1, new Text("Description:"), new Text(company.getDescription()));

        Text managerInfoText = new Text("\nManager Information:");
        managerInfoText.setFont(Font.font(null, FontWeight.BOLD, 14));
        GridPane.setConstraints(managerInfoText, 0, 2, 2, 1); // Span 2 columns, row 2
        gridPane.getChildren().add(managerInfoText);

        gridPane.addRow(3, new Text("City:"), new Text(company.getCity()));

        gridPane.addRow(4, new Text("Username:"), new Text(company.getManager().getUsername()));
        gridPane.addRow(5, new Text("Email:"), new Text(company.getManager().getEmail()));
        gridPane.addRow(6, new Text("Birth Date:"), new Text(epochConverter.toLocalDate(company.getManager().getBirthDate()).toString()));
        gridPane.addRow(7, new Text("First Name:"), new Text(company.getManager().getFirstName()));
        gridPane.addRow(8, new Text("Last Name:"), new Text(company.getManager().getLastName()));
        gridPane.addRow(9, new Text("Started Working:"), new Text(epochConverter.toLocalDate(company.getManager().getStartedWorking()).toString()));

        return gridPane;
    }
}
