package com.raf.rentingnotificationservice.controller;

import com.raf.rentingnotificationservice.dto.ActivationDto;
import com.raf.rentingnotificationservice.service.ActivationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/activation")
public class ActivationController {

    private ActivationService activationService;

    public ActivationController(ActivationService activationService) {
        this.activationService = activationService;
    }

    @GetMapping
    public ResponseEntity<Page<ActivationDto>> getAll(Pageable pageable) {

        return new ResponseEntity<>(activationService.findAll(pageable), HttpStatus.OK);
    }

//    @PostMapping
    public void addNew(@RequestBody @Valid ActivationDto activationDto) {
        activationService.add(activationDto);
    }


//    @PutMapping("/edit/{id}")
//    public ResponseEntity<ActivationDto> editManager(@PathVariable("id") Long id,
//                                                     @RequestBody ActivationCreateDto activationCreateDto){
//        return new ResponseEntity<>(activationService.edit(id, activationCreateDto), HttpStatus.ACCEPTED);
//    }
}
