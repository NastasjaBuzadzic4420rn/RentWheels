package gui.view;

import gui.restclient.dto.CompanyDto;
import gui.restclient.dto.ManagerDto;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ManagerView extends VBox {

    public ManagerView(CompanyDto companyDto) {
        if(companyDto != null && companyDto.isApproved())
            this.getChildren().add(new Label("This company is approved"));
        else
            this.getChildren().add(new Label("This company not is approved"));

    }
}
