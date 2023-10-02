package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.UserStatus;
import com.raf.restdemo.dto.UserStatusDto;
import org.springframework.stereotype.Component;

@Component
public class UserStatusMapper {

    public UserStatus userStatusDtoToUserStatus(UserStatusDto userStatusDto){
        UserStatus userStatus = new UserStatus();
        userStatus.setDiscount(userStatusDto.getDiscount());
        userStatus.setRank(userStatusDto.getRank());
        userStatus.setMinPoints(userStatusDto.getMinPoints());
        userStatus.setMaxPoints(userStatusDto.getMaxPoints());
        return userStatus;
    }

    public UserStatusDto userStatusToUserStatusDto(UserStatus userStatusDto){
        UserStatusDto userStatus = new UserStatusDto();
        userStatus.setDiscount(userStatusDto.getDiscount());
        userStatus.setRank(userStatusDto.getRank());
        userStatus.setMinPoints(userStatusDto.getMinPoints());
        userStatus.setMaxPoints(userStatusDto.getMaxPoints());
        return userStatus;
    }
}
