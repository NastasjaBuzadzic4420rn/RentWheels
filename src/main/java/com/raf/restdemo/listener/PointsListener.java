package com.raf.restdemo.listener;

import com.raf.restdemo.comunication.PointsDto;
import com.raf.restdemo.listener.helper.MessageHelper;
import com.raf.restdemo.service.ClientService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class PointsListener {
    private MessageHelper messageHelper;
    private ClientService clientService;

    public PointsListener(MessageHelper messageHelper, ClientService clientService) {
        this.messageHelper = messageHelper;
        this.clientService = clientService;
    }

    @JmsListener(destination = "${destination.points}", concurrency = "5-10")
    public void changePoints(Message message) throws JMSException {
        PointsDto pointsDto = messageHelper.getMessage(message, PointsDto.class);
        clientService.changePoints(pointsDto);
    }
}
