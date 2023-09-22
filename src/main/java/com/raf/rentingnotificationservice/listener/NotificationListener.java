package com.raf.rentingnotificationservice.listener;

import com.raf.rentingnotificationservice.dto.NotificationDto;
import com.raf.rentingnotificationservice.listener.helper.MessageHelper;
import com.raf.rentingnotificationservice.service.NotificationService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class NotificationListener {

    private MessageHelper messageHelper;
    private NotificationService notificationService;

    public NotificationListener(MessageHelper messageHelper, NotificationService notificationService) {
        this.messageHelper = messageHelper;
        this.notificationService = notificationService;
    }

    @JmsListener(destination = "${destination.notification}", concurrency = "5-10")
    public void sendNotificationEmail(Message message) throws JMSException{
        try {
            NotificationDto notificationDto = messageHelper.getMessage(message, NotificationDto.class);
            notificationService.sendMail(notificationDto);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }


}


