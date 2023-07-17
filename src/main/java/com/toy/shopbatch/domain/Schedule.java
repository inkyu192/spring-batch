package com.toy.shopbatch.domain;

import com.toy.shopbatch.dto.ScheduleDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseDomain {

    @Id
    @GeneratedValue
    @Column(name = "schedule_id")
    private Long id;
    private String cron;
    private String jobName;
    private String useYn;

    public static Schedule createSchedule(ScheduleDto.Save requestDto) {
        Schedule schedule = new Schedule();
        schedule.cron = requestDto.getCron();
        schedule.jobName = requestDto.getJobName();
        schedule.useYn = requestDto.getUseYn();

        return schedule;
    }

    public void updateSchedule(ScheduleDto.Update requestDto) {
        this.cron = requestDto.getCron();
        this.jobName = requestDto.getJobName();
        this.useYn = requestDto.getUseYn();
    }
}
