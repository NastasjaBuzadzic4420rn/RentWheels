package com.raf.rentingnotificationservice.listener;

import com.raf.rentingnotificationservice.dto.ActivationDto;
import com.raf.rentingnotificationservice.dto.ReservationDto;
import com.raf.rentingnotificationservice.listener.helper.MessageHelper;
import com.raf.rentingnotificationservice.service.ActivationService;
import com.raf.rentingnotificationservice.service.ReservationService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class ActivationListener {

    private MessageHelper messageHelper;
    private ActivationService activationService;

    public ActivationListener(MessageHelper messageHelper, ActivationService activationService) {
        this.messageHelper = messageHelper;
        this.activationService = activationService;
    }

    @JmsListener(destination = "${destination.activation}", concurrency = "5-10")
    public void sendActivationEmail(Message message) throws JMSException{
        ActivationDto activationDto = messageHelper.getMessage(message, ActivationDto.class);
        System.out.println(activationDto);
        activationService.add(activationDto);
    }


}


