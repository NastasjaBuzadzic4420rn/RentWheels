package com.raf.restdemo.controller;

import com.raf.restdemo.dto.*;
import com.raf.restdemo.listener.helper.MessageHelper;
import com.raf.restdemo.security.CheckSecurity;
import com.raf.restdemo.service.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<Page<ClientDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
                                                       Pageable pageable) {
        return new ResponseEntity<>(clientService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"admin", "manager", "client"})
    public ResponseEntity<ClientDto> getUserById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDto> saveUser(@RequestBody @Valid ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(clientService.add(clientCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/confirmRegistration")
    public ResponseEntity<ClientDto> confirm(@RequestBody @Valid ActivationDto activationDto){
        return new ResponseEntity<>(clientService.confirm(activationDto), HttpStatus.ACCEPTED);
    }

    @PutMapping("/edit/{id}")
    @CheckSecurity(roles = {"admin", "client"})
    public ResponseEntity<ClientDto> editClient(@RequestHeader("Authorization") String authorization,
                                                @PathVariable("id") Long id, @RequestBody ClientCreateDto clientCreateDto){
        return new ResponseEntity<>(clientService.edit(id, clientCreateDto), HttpStatus.ACCEPTED);
    }

    @PutMapping("/deactivate/{id}")
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<ClientDto> deactivateManager(@RequestHeader("Authorization") String authorization,
                                                      @PathVariable("id") Long id){
        return new ResponseEntity<> (clientService.changeActivity(id, false), HttpStatus.ACCEPTED);
    }

    @PutMapping("/activate/{id}")
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<ClientDto> activateManager(@RequestHeader("Authorization") String authorization,
                                                      @PathVariable("id") Long id) {
        return new ResponseEntity<> (clientService.changeActivity(id, true), HttpStatus.ACCEPTED);
    }

//    @PostMapping("/login")
//    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
//        return new ResponseEntity<>(clientService.login(tokenRequestDto), HttpStatus.OK);
//    }
}
