package com.raf.rentingnotificationservice.mapper;

import com.raf.rentingnotificationservice.domain.History;
import com.raf.rentingnotificationservice.dto.HistoryCreateDto;
import com.raf.rentingnotificationservice.dto.HistoryDto;
import org.springframework.stereotype.Component;

@Component
public class HistoryMapper {

    public HistoryDto historyToHistoryDto(History history){
        HistoryDto historyDto = new HistoryDto();
        historyDto.setId(history.getId());
        historyDto.setText(history.getText());
        historyDto.setType(history.getType());
        historyDto.setEmailTo(history.getEmailTo());
        return historyDto;
    }

    public History historyCreateDtoToHistory(HistoryCreateDto historyCreateDto){
        History history = new History();
        history.setType(historyCreateDto.getType());
        history.setText(historyCreateDto.getText());
        history.setEmailTo(historyCreateDto.getEmailTo());
        return history;
    }

}
