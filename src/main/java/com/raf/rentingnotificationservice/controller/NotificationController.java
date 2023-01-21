package com.raf.rentingnotificationservice.controller;

import com.raf.rentingnotificationservice.dto.NotificationCreateDto;
import com.raf.rentingnotificationservice.dto.NotificationDto;
import com.raf.rentingnotificationservice.security.CheckSecurity;
import com.raf.rentingnotificationservice.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private NotificationService notificationService;

    @GetMapping
//    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<Page<NotificationDto>> getAll(Pageable pageable) {

        return new ResponseEntity<>(notificationService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
//    @CheckSecurity(roles = {"admin"})
    public void addNew(@RequestBody @Valid NotificationCreateDto notificationCreateDto) {
        notificationService.add(notificationCreateDto);
    }

    @PutMapping("/edit/{id}")
//    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<NotificationDto> edit(@PathVariable("id") Long id, @RequestBody NotificationCreateDto notificationCreateDto){
        return new ResponseEntity<>(notificationService.edit(id, notificationCreateDto), HttpStatus.ACCEPTED);
    }


}
