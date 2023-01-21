package com.raf.rentingnotificationservice.repository;

import com.raf.rentingnotificationservice.domain.History;
import com.raf.rentingnotificationservice.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
}
