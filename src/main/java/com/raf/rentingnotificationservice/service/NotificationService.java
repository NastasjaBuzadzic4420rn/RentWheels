package com.raf.rentingnotificationservice.service;

import com.raf.rentingnotificationservice.dto.NotificationCreateDto;
import com.raf.rentingnotificationservice.dto.NotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService {
    void add(NotificationCreateDto notificationCreateDto);

    Page<NotificationDto> findAll(Pageable pageable);

    NotificationDto edit(Long id, NotificationCreateDto notificationCreateDto);

    void sendMail(NotificationDto notificationDto);

}
