package com.raf.rentingnotificationservice.listener;

import com.raf.rentingnotificationservice.dto.ReservationDto;
import com.raf.rentingnotificationservice.listener.helper.MessageHelper;
import com.raf.rentingnotificationservice.service.ActivationService;
import com.raf.rentingnotificationservice.service.ReservationService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class ReservationListener {

    private MessageHelper messageHelper;
    private ReservationService reservationService;

    public ReservationListener(MessageHelper messageHelper, ReservationService reservationService) {
        this.messageHelper = messageHelper;
        this.reservationService = reservationService;
    }

    @JmsListener(destination = "${destination.reservation}", concurrency = "5-10")
    public void sendReservationEmail(Message message) throws JMSException {
        ReservationDto reservationDto = messageHelper.getMessage(message, ReservationDto.class);
        reservationService.add(reservationDto);
    }

}
