package com.raf.rentingnotificationservice.service.impl;

import com.raf.rentingnotificationservice.domain.History;
import com.raf.rentingnotificationservice.dto.HistoryCreateDto;
import com.raf.rentingnotificationservice.dto.HistoryDto;
import com.raf.rentingnotificationservice.mapper.exception.NotFoundException;
import com.raf.rentingnotificationservice.mapper.HistoryMapper;
import com.raf.rentingnotificationservice.repository.HistoryRepository;
import com.raf.rentingnotificationservice.service.HistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    private HistoryRepository historyRepository;
    private HistoryMapper historyMapper;


    public HistoryServiceImpl(HistoryRepository historyRepository, HistoryMapper historyMapper) {
        this.historyRepository = historyRepository;
        this.historyMapper = historyMapper;
    }

    @Override
    public void add(HistoryCreateDto historyCreateDto) {
        History history = historyMapper.historyCreateDtoToHistory(historyCreateDto);
        System.out.println("Ubacen history " + history);
        historyRepository.save(history);
    }

    @Override
    public Page<HistoryDto> findAll(Pageable pageable) {
        return historyRepository.findAll(pageable)
                .map(historyMapper::historyToHistoryDto);
    }

    @Override
    public HistoryDto edit(Long id, HistoryCreateDto historyCreateDto) {
        History history = historyRepository.findById(id).orElseThrow(() ->
                new NotFoundException("This email does not exist."));
        history.setText(historyCreateDto.getText());
        history.setType(historyCreateDto.getType());
        history.setEmailTo(historyCreateDto.getEmailTo());
        historyRepository.save(history);
        return historyMapper.historyToHistoryDto(history);
    }
}
