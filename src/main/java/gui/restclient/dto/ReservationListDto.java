package gui.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class ReservationListDto {

	private List<ReservationDto> content = new ArrayList<>();

	public ReservationListDto() {

	}

	public ReservationListDto(List<ReservationDto> content) {
		this.content = content;
	}

	public List<ReservationDto> getContent() {
		return content;
	}

	public void setContent(List<ReservationDto> content) {
		this.content = content;
	}
}
