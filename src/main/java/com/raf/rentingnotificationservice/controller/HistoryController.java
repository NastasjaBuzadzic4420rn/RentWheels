package com.raf.rentingnotificationservice.controller;

import com.raf.rentingnotificationservice.dto.HistoryCreateDto;
import com.raf.rentingnotificationservice.dto.HistoryDto;
import com.raf.rentingnotificationservice.security.CheckSecurity;
import com.raf.rentingnotificationservice.service.HistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private HistoryService historyService;

    @GetMapping
//    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<Page<HistoryDto>> getAll(Pageable pageable) {
        return new ResponseEntity<>(historyService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public void addNew(@RequestBody @Valid HistoryCreateDto historyCreateDto) {
        historyService.add(historyCreateDto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<HistoryDto> edit(@PathVariable("id") Long id, @RequestBody HistoryCreateDto historyCreateDto){
        return new ResponseEntity<>(historyService.edit(id, historyCreateDto), HttpStatus.ACCEPTED);
    }
}
