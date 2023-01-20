package com.raf.rentingnotificationservice.controller;

import com.raf.rentingnotificationservice.dto.NotificationCreateDto;
import com.raf.rentingnotificationservice.dto.NotificationDto;
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
    public ResponseEntity<Page<NotificationDto>> getAll(Pageable pageable) {

        return new ResponseEntity<>(notificationService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public void addNew(@RequestBody @Valid NotificationCreateDto notificationCreateDto) {
        notificationService.add(notificationCreateDto);
    }


//    @PutMapping("/edit/{id}")
//    public ResponseEntity<NotificationDto> editManager(@PathVariable("id") Long id,
//                                                       @RequestBody NotificationCreateDto activationCreateDto){
//        return new ResponseEntity<>(notificationService.edit(id, activationCreateDto), HttpStatus.ACCEPTED);
//    }
}
