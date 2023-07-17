package com.toy.shopbatch.service;

import com.toy.shopbatch.domain.Schedule;
import com.toy.shopbatch.dto.ScheduleDto;
import com.toy.shopbatch.quartz.QuartzUtil;
import com.toy.shopbatch.repository.ScheduleRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final QuartzUtil quartzUtil;

    @Override
    @Transactional
    public ScheduleDto.Response saveSchedule(ScheduleDto.Save requestDto) {
        Schedule schedule = Schedule.createSchedule(requestDto);

        scheduleRepository.save(schedule);

        quartzUtil.addScheduleJob(String.valueOf(schedule.getId()), schedule.getJobName(), schedule.getCron());

        return new ScheduleDto.Response(schedule);
    }

    @Override
    public List<ScheduleDto.Response> schedules() {
        List<Schedule> list = scheduleRepository.findAll("Y");

        return list.stream().map(ScheduleDto.Response::new).toList();
    }

    @Override
    public ScheduleDto.Response schedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(NoResultException::new);

        return new ScheduleDto.Response(schedule);
    }

    @Override
    @Transactional
    public ScheduleDto.Response updateSchedule(Long id, ScheduleDto.Update requestDto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(NoResultException::new);

        schedule.updateSchedule(requestDto);

        if (schedule.getUseYn().equals("Y")) {
            quartzUtil.updateScheduleJob(String.valueOf(schedule.getId()), schedule.getCron());
        } else {
            quartzUtil.deleteScheduleJob(String.valueOf(schedule.getId()));
        }

        return new ScheduleDto.Response(schedule);
    }

    @Override
    @Transactional
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(NoResultException::new);

        scheduleRepository.delete(schedule);

        quartzUtil.deleteScheduleJob(String.valueOf(schedule.getId()));
    }
}
