package com.raf.restdemo.repository;

import com.raf.restdemo.domain.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {

    Optional<Page<UserStatus>> findUserStatusByMaxPointsGreaterThanAndMinPointsLessThan(int maxPoints, int minPoints, Pageable pageable);
}
