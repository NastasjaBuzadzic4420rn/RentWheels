package com.raf.rentingnotificationservice.mapper;

import com.raf.rentingnotificationservice.domain.Notification;
import com.raf.rentingnotificationservice.dto.NotificationCreateDto;
import com.raf.rentingnotificationservice.dto.NotificationDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public Notification notificationDtoToNotification(NotificationCreateDto dto){
        Notification notification = new Notification();
        notification.setText(dto.getText());
        notification.setParameters(dto.getParameters());
        notification.setType(dto.getType());
        return notification;
    }
}
