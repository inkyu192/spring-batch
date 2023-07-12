package com.toy.shopbatch.repository;

import com.toy.shopbatch.domain.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Scheduler, Long> {
}
