package com.raf.rentingnotificationservice.service.impl;

import com.raf.rentingnotificationservice.domain.Notification;
import com.raf.rentingnotificationservice.dto.ReceivedNotifDto;
import com.raf.rentingnotificationservice.mapper.exception.NotFoundException;
import com.raf.rentingnotificationservice.mapper.NotificationMapper;
import com.raf.rentingnotificationservice.repository.NotificationRepository;
import com.raf.rentingnotificationservice.service.HistoryService;
import com.raf.rentingnotificationservice.service.NotificationService;
import com.raf.rentingnotificationservice.service.email.EmailService;
import com.raf.rentingnotificationservice.dto.HistoryCreateDto;
import com.raf.rentingnotificationservice.dto.NotificationCreateDto;
import com.raf.rentingnotificationservice.dto.NotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private NotificationMapper notificationMapper;
    private NotificationRepository notificationRepository;

    private EmailService emailService;
//    private JavaMailSender mailSender;

    private HistoryService historyService;

    public NotificationServiceImpl(NotificationMapper notificationMapper, NotificationRepository notificationRepository, EmailService emailService, HistoryService historyService) {
        this.notificationMapper = notificationMapper;
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
        this.historyService = historyService;
    }

    @Override
    public void add(NotificationCreateDto notificationCreateDto) {
        Notification notification = notificationMapper.notificationCreateDtoToNotification(notificationCreateDto);
        notificationRepository.save(notification);

    }

    @Override
    public Page<NotificationDto> findAll(Pageable pageable) {
        return notificationRepository.findAll(pageable)
                .map(notificationMapper::notificationToNotificationDto);
    }

    @Override
    public NotificationDto edit(Long id, NotificationCreateDto notificationCreateDto) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() ->
                new NotFoundException("This notification does not exist."));
        notification.setText(notificationCreateDto.getText());
        notification.setParameters(notificationCreateDto.getParameters());
        notification.setType(notificationCreateDto.getType());
        notificationRepository.save(notification);
        return notificationMapper.notificationToNotificationDto(notification);
    }

    @Override
    public void sendMail(ReceivedNotifDto receivedNotifDto) {
        System.out.println(receivedNotifDto.getParameters());
        List<String> args = List.of(receivedNotifDto.getParameters().split(", "));

        Notification notification = notificationRepository.findByType(receivedNotifDto.getType()).get();
        String message = String.format(notification.getText(), args.toArray());

        System.out.println(message);

        HistoryCreateDto historyCreateDto = new HistoryCreateDto();
        historyCreateDto.setEmailTo(receivedNotifDto.getRecever());
        historyCreateDto.setType(notification.getType());
        historyCreateDto.setText(message);
        historyService.add(historyCreateDto);

        emailService.sendSimpleMessage(receivedNotifDto.getRecever(), notification.getType(), message);




//        SimpleMailMessage message = new SimpleMailMessage();
////        message.setTo(activationDto.getEmail());
//        message.setText(text);
//        mailSender.send(message);
    }
}
