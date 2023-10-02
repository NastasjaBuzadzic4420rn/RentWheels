package com.raf.restdemo.service;

import com.raf.restdemo.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserStatusService {

    UserStatusDto add(UserStatusDto userStatusDto);

    Page<UserStatusDto> getForPoints(Long userId, Pageable pageable);

    Page<UserStatusDto> findAll(Pageable pageable);

    UserStatusDto edit(Long id, UserStatusDto userStatusDto);


}
