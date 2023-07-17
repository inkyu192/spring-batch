package com.toy.shopbatch.service;

import com.toy.shopbatch.dto.ScheduleDto;

import java.util.List;

public interface ScheduleService {

    ScheduleDto.Response saveSchedule(ScheduleDto.Save requestDto);

    List<ScheduleDto.Response> schedules();

    ScheduleDto.Response schedule(Long id);

    ScheduleDto.Response updateSchedule(Long id, ScheduleDto.Update requestDto);

    void deleteSchedule(Long id);
}
