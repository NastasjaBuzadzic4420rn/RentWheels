package com.example.reservationservice.comunication;


import com.example.reservationservice.comunication.dto.NotificationDto;
import com.example.reservationservice.dto.*;

public class CreateNotification {

    public NotificationDto waitForApproveNotification(CompanyManagerDto companyManagerDto){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setReceiver(companyManagerDto.getEmail());
        notificationDto.setType("WAIT_FOR_APPROVE");
        String param = companyManagerDto.getFirstName() + ", " + companyManagerDto.getFirstName() + ", " +
                companyManagerDto.getLastName() + ", " + companyManagerDto.getUsername() + ", " + companyManagerDto.getEmail();
        notificationDto.setParameters(param);
        return  notificationDto;
    }



}
