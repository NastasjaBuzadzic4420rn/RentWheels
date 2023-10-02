package gui.mapper;

import gui.model.Company;
import gui.restclient.dto.CompanyDto;
import gui.restclient.dto.ManagerDto;

public class CompanyMapper {

    public Company companyDtoToCompany(CompanyDto companyDto){
        Company company = new Company();
        company.setId(companyDto.getId());
        company.setName(companyDto.getName());
        company.setDescription(companyDto.getDescription());
        company.setNumOfVehicles(companyDto.getNumOfVehicles());
        company.setCity(companyDto.getCity());
        company.setApproved(companyDto.isApproved());
        return company;
    }
}
