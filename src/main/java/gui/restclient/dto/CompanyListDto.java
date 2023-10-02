package gui.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class CompanyListDto {

    List<CompanyDto> content = new ArrayList<>();

    public List<CompanyDto> getContent() {
        return content;
    }

    public void setContent(List<CompanyDto> content) {
        this.content = content;
    }
}
