package com.toy.shopbatch.repository;

import com.toy.shopbatch.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule s where s.useYn = :useYn")
    List<Schedule> findAll(@Param("useYn") String useYn);
}
