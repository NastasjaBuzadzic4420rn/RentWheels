package gui.view;

import gui.controller.ApproveCompanyController;
import gui.controller.LoadCompaniesController;
import gui.model.Company;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


public class AdminView extends VBox {
    private Label titleLabel;
    private ToggleButton changeButton;
    private TableView<Company> tableView;
    private ObservableList<Company> allCompanies;
    private ObservableList<Company> notApprovedCompanies;
    private Button approveButton;
    private LoadCompaniesController loadCompaniesController;


    public AdminView() {
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10));

        initViewElements();
        initListeners();
        loadData();
        addViewElements();
    }

    private void initViewElements(){
        titleLabel = new Label("Managing users");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        tableView = new TableView<>();
        tableView.setItems(allCompanies);

        TableColumn<Company, String> companyColumn = new TableColumn<>("Company name");
        companyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<Company, String> managerColumn = new TableColumn<>("Manager");
        managerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getManager().getFirstName() + " " + cellData.getValue().getManager().getLastName()));

        TableColumn<Company, String> cityColumn = new TableColumn<>("City");
        cityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCity()));

        tableView.setRowFactory(tv -> {
            TableRow<Company> row = new TableRow<>();
            row.itemProperty().addListener((obs, oldCompany, newCompany) -> {
                if (newCompany != null) {
                    if (!newCompany.isApproved()) {
                        row.setStyle("-fx-background-color: lightcoral; -fx-border-color: grey;");
                    } else {
                        row.setStyle("");
                    }
                }
            });
            return row;
        });

        tableView.getColumns().addAll(companyColumn, managerColumn, cityColumn);


        approveButton = new Button("Approve");
        approveButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
    }

    private void initListeners(){
        approveButton.setOnAction(new ApproveCompanyController(this));
    }

    private void loadData(){
        loadCompaniesController = new LoadCompaniesController(this);
        loadCompaniesController.loadData();
    }

    private void addViewElements(){
        getChildren().addAll(titleLabel, tableView, approveButton);
    }


    public ObservableList<Company> getAllCompanies() {
        return allCompanies;
    }

    public void setAllCompanies(ObservableList<Company> allCompanies) {
        this.allCompanies = allCompanies;
    }

    public ObservableList<Company> getNotApprovedCompanies() {
        return notApprovedCompanies;
    }

    public void setNotApprovedCompanies(ObservableList<Company> notApprovedCompanies) {
        this.notApprovedCompanies = notApprovedCompanies;
    }

    public TableView<Company> getTableView() {
        return tableView;
    }

    public LoadCompaniesController getLoadCompaniesController() {
        return loadCompaniesController;
    }
}
