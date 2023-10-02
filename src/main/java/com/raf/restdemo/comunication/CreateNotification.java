package com.raf.restdemo.comunication;

import com.raf.restdemo.domain.Client;
import com.raf.restdemo.domain.Manager;
import com.raf.restdemo.dto.ClientCreateDto;
import com.raf.restdemo.dto.ManagerCreateDto;
import com.raf.restdemo.epoch.EpochConverter;

public class CreateNotification {

    private EpochConverter epochConverter;

    public CreateNotification() {
        this.epochConverter = new EpochConverter();
    }

    public NotificationDto confirmClientNotification(ClientCreateDto clientCreateDto, String activationCode){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setReceiver(clientCreateDto.getEmail());
        notificationDto.setType("CONFIRM_CLIENT");
        String param = clientCreateDto.getFirstName() + ", " + clientCreateDto.getLastName() + ", " +
                clientCreateDto.getEmail() + ", " + activationCode;
        notificationDto.setParameters(param);
        return  notificationDto;
    }

    public NotificationDto confirmManagerNotification(ManagerCreateDto managerCreateDto, String activationCode){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setReceiver(managerCreateDto.getEmail());
        notificationDto.setType("CONFIRM_MANAGER");
        String param = managerCreateDto.getFirstName() + ", " + managerCreateDto.getLastName() + ", " +
                managerCreateDto.getEmail() + ", " + activationCode;
        notificationDto.setParameters(param);
        return  notificationDto;
    }

    public NotificationDto waitForApproveNotification(Manager manager){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setReceiver(manager.getEmail());
        notificationDto.setType("WAIT_FOR_APPROVE");
        String param = manager.getFirstName() + ", " + manager.getFirstName() + ", " +
                manager.getLastName() + ", " + manager.getUsername() + ", " + manager.getEmail();
        notificationDto.setParameters(param);
        return  notificationDto;
    }

    public NotificationDto rentCar(Client client, PointsDto pointsDto) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setType("RENT_VEHICLE");
        notificationDto.setReceiver(client.getEmail());

        String param = client.getFirstName() + ", " + client.getLastName() + ", " + pointsDto.getType() + ", " +
                pointsDto.getManufacturer() + ", " + pointsDto.getModel() + ", " + epochConverter.toLocalDate(pointsDto.getStartDate()).toString() + ", " + epochConverter.toLocalDate(pointsDto.getEndDate()).toString() + ", " +
                pointsDto.getManufacturer() + ", " + pointsDto.getModel() + ", " + pointsDto.getType() + ", " +  String.format("%.2f", pointsDto.getPrice()) + ", " +
                epochConverter.toLocalDate(pointsDto.getStartDate()).toString() + ", " + epochConverter.toLocalDate(pointsDto.getEndDate()).toString() + ", " + pointsDto.getCompany() + ", " + pointsDto.getCity();
        notificationDto.setParameters(param);
        return  notificationDto;
    }


    public NotificationDto cancelReservation(Client client, PointsDto pointsDto) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setType("CANCEL_RESERVATION");
        notificationDto.setReceiver(client.getEmail());

        String param = client.getFirstName() + ", " + client.getLastName() + ", " + pointsDto.getCompany() + ", " + pointsDto.getType() + ", " +
        pointsDto.getManufacturer() + ", " + pointsDto.getModel() + ", " + epochConverter.toLocalDate(pointsDto.getStartDate()).toString() + ", " + epochConverter.toLocalDate(pointsDto.getEndDate()).toString() + ", " +
                pointsDto.getCompany();
        notificationDto.setParameters(param);
        return  notificationDto;
    }

}
