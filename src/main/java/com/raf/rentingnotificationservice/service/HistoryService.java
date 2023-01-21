package com.raf.rentingnotificationservice.service;

import com.raf.rentingnotificationservice.dto.HistoryCreateDto;
import com.raf.rentingnotificationservice.dto.HistoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoryService {

    Page<HistoryDto> findAll(Pageable pageable);

    void add(HistoryCreateDto historyCreateDto);

    HistoryDto edit(Long id, HistoryCreateDto historyCreateDto);
}
