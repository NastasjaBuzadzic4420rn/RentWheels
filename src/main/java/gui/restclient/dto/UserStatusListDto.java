package gui.restclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserStatusListDto {
    private List<UserStatusDto> content = new ArrayList<>();

    public UserStatusListDto(List<UserStatusDto> content) {
        this.content = content;
    }

    public UserStatusListDto() {
    }

    public List<UserStatusDto> getContent() {
        return content;
    }

    public void setContent(List<UserStatusDto> content) {
        this.content = content;
    }
}
