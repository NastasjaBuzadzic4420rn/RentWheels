package com.raf.rentingnotificationservice.service.impl;

import com.raf.rentingnotificationservice.domain.Notification;
import com.raf.rentingnotificationservice.dto.ActivationDto;
import com.raf.rentingnotificationservice.dto.NotificationCreateDto;
import com.raf.rentingnotificationservice.dto.NotificationDto;
import com.raf.rentingnotificationservice.mapper.NotificationMapper;
import com.raf.rentingnotificationservice.repository.NotificationRepository;
import com.raf.rentingnotificationservice.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private NotificationMapper notificationMapper;
    private NotificationRepository notificationRepository;
    public JavaMailSender mailSender;

    @Override
    public void add(NotificationCreateDto notificationCreateDto) {
        Notification notification = notificationMapper.notificationDtoToNotification(notificationCreateDto);
        notificationRepository.save(notification);

    }

    @Override
    public Page<NotificationDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void sendMail(ActivationDto activationDto, String type, String text) {

//        (Pozdrav %ime %prezime, da
//        bi ste se verifikovali idite na sledeci %link).
//        TODO: poruka je u ovom obliku, sto znaci da su posle % navedeni parametri koje prima
        List<String> split = List.of(text.split(" "));
        for(String s:split){
            if(s.startsWith("%")) {
                text.replace(s, activationDto.getUsername());
            }
        }


        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(activationDto.getEmail());
        message.setText(text);
        mailSender.send(message);
    }
}
