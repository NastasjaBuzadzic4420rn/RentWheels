package gui.controller;

import gui.mapper.CompanyMapper;
import gui.mapper.ManagerMapper;
import gui.model.Company;
import gui.model.Manager;
import gui.restclient.ReservationServiceRestClient;
import gui.restclient.UserServiceRestClient;
import gui.restclient.dto.CompanyDto;
import gui.restclient.dto.ManagerDto;
import gui.view.AdminView;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadCompaniesController {

    private AdminView adminView;
    private UserServiceRestClient userServiceRestClient;
    private ReservationServiceRestClient reservationServiceRestClient;
    private CompanyMapper companyMapper;
    private ManagerMapper managerMapper;

    public LoadCompaniesController(AdminView adminView) {
        this.adminView = adminView;
        this.userServiceRestClient = new UserServiceRestClient();
        this.reservationServiceRestClient = new ReservationServiceRestClient();
        this.companyMapper = new CompanyMapper();
        this.managerMapper = new ManagerMapper();
    }

    public void loadData(){
        try {
            List<Company> companies = new ArrayList<>();

            List<CompanyDto> companyDtos =  reservationServiceRestClient.getAllCompanies();
            for (CompanyDto companyDto : companyDtos){
                Company company = companyMapper.companyDtoToCompany(companyDto);

                ManagerDto managerDto = userServiceRestClient.getManager(companyDto.getManagerId());
                Manager manager = managerMapper.managerDtoToManager(managerDto);

                company.setManager(manager);
                companies.add(company);
            }

            adminView.setAllCompanies(FXCollections.observableArrayList(companies));
            adminView.getTableView().setItems(adminView.getAllCompanies());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
