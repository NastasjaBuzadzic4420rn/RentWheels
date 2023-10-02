package com.raf.restdemo.controller;

import com.raf.restdemo.dto.*;
import com.raf.restdemo.listener.helper.MessageHelper;
import com.raf.restdemo.security.CheckSecurity;
import com.raf.restdemo.service.ManagerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private ManagerService managerService;


    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<Page<ManagerDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
                                                        Pageable pageable) {
        return new ResponseEntity<>(managerService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"admin", "manager"})
    public ResponseEntity<ManagerDto> getManager(@RequestHeader("Authorization") String authorization,
                                                 @PathVariable("id") Long id) {
        return new ResponseEntity<>(managerService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/byUsername/{username}")
    @CheckSecurity(roles = {"admin", "manager"})
    public ResponseEntity<ManagerDto> getManagerByUsername(@RequestHeader("Authorization") String authorization,
                                                           @PathVariable("username") String username) {
        return new ResponseEntity<>(managerService.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ManagerDto> saveUser(@RequestBody @Valid ManagerCreateDto managerCreateDto) {
        return new ResponseEntity<>(managerService.add(managerCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/confirmRegistration")
    public ResponseEntity<ManagerDto> confirm(@RequestBody @Valid ActivationDto activationDto){
        return new ResponseEntity<>(managerService.confirm(activationDto), HttpStatus.ACCEPTED);
    }


    @PutMapping("/edit/{id}")
    @CheckSecurity(roles = {"admin", "manager"})
    public ResponseEntity<ManagerDto> editManager(@RequestHeader("Authorization") String authorization,
                                                  @PathVariable("id") Long id, @RequestBody ManagerCreateDto managerCreateDto){
        return new ResponseEntity<>(managerService.edit(id, managerCreateDto), HttpStatus.ACCEPTED);
    }

    @PutMapping("/deactivate/{id}")
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<ManagerDto> deactiveManager(@RequestHeader("Authorization") String authorization,
                                                      @PathVariable("id") Long id){
        return new ResponseEntity<> (managerService.changeActivity(id, false), HttpStatus.ACCEPTED);
    }

    @PutMapping("/activate/{id}")
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<ManagerDto> activateManager(@RequestHeader("Authorization") String authorization,
                                                      @PathVariable("id") Long id) {
        return new ResponseEntity<> (managerService.changeActivity(id, true), HttpStatus.ACCEPTED);
    }


//    @PostMapping("/login")
//    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
//        return new ResponseEntity<>(managerService.login(tokenRequestDto), HttpStatus.OK);
//    }

}
