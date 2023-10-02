package gui.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class CompanyVehicleListDto {
    private List<CompanyVehicleDto> content = new ArrayList<>();

    public CompanyVehicleListDto() {
    }

    public CompanyVehicleListDto(List<CompanyVehicleDto> content) {
        this.content = content;
    }

    public List<CompanyVehicleDto> getContent() {
        return content;
    }

    public void setContent(List<CompanyVehicleDto> content) {
        this.content = content;
    }
}
