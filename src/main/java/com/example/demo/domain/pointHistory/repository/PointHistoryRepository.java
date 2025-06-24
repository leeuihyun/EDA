package com.example.demo.domain.pointHistory.repository;

import com.example.demo.domain.pointHistory.entity.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {

}
