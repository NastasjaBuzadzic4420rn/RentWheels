package com.raf.rentingnotificationservice.service;

import com.raf.rentingnotificationservice.domain.Notification;
import com.raf.rentingnotificationservice.dto.ActivationDto;
import com.raf.rentingnotificationservice.dto.NotificationCreateDto;
import com.raf.rentingnotificationservice.dto.NotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService {
    void add(NotificationCreateDto notificationCreateDto);

    Page<NotificationDto> findAll(Pageable pageable);

    void sendMail(ActivationDto activationDto, String type, String text);
}
