package com.raf.restdemo.controller;

import com.raf.restdemo.dto.UserStatusDto;
import com.raf.restdemo.security.CheckSecurity;
import com.raf.restdemo.service.UserStatusService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/status")
public class UserStatusController{

    private UserStatusService userStatusService;

    public UserStatusController(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }

    @GetMapping
    @CheckSecurity(roles = {"admin", "manager"})
    public ResponseEntity<Page<UserStatusDto>> getAll(@RequestHeader("Authorization") String authorization,
                                                           Pageable pageable) {

        return new ResponseEntity<>(userStatusService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/getStatusFor/{userId}")
    @CheckSecurity(roles = {"admin", "manager", "client"})
    public ResponseEntity<Page<UserStatusDto>> getForPoints(@RequestHeader("Authorization") String authorization,
                                                            @PathVariable("userId") Long userId, Pageable pageable) {
        return new ResponseEntity<>(userStatusService.getForPoints(userId, pageable), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<UserStatusDto> addNew(@RequestHeader("Authorization") String authorization,
                                                @RequestBody @Valid UserStatusDto clientCreateDto) {
        return new ResponseEntity<>(userStatusService.add(clientCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    @CheckSecurity(roles = {"admin", "manager"})
    public ResponseEntity<UserStatusDto> editStatus(@RequestHeader("Authorization") String authorization,
                                                     @PathVariable("id") Long id,
                                                     @RequestBody UserStatusDto managerCreateDto){
        return new ResponseEntity<>(userStatusService.edit(id, managerCreateDto), HttpStatus.ACCEPTED);
    }
}
