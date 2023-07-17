package com.toy.shopbatch.controller;

import com.toy.shopbatch.common.ResultDto;
import com.toy.shopbatch.dto.ScheduleDto;
import com.toy.shopbatch.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public Object saveSchedule(@RequestBody @Valid ScheduleDto.Save requestDto) {
        ScheduleDto.Response responseDto = scheduleService.saveSchedule(requestDto);

        return new ResultDto<>(responseDto);
    }

    @GetMapping
    public Object schedules() {
        List<ScheduleDto.Response> list = scheduleService.schedules();

        return new ResultDto<>(list);
    }

    @GetMapping("{id}")
    public Object schedule(@PathVariable Long id) {
        ScheduleDto.Response responseDto = scheduleService.schedule(id);

        return new ResultDto<>(responseDto);
    }

    @PatchMapping("{id}")
    public Object updateSchedule(@PathVariable Long id,
                                 @RequestBody @Valid ScheduleDto.Update requestDto) {
        ScheduleDto.Response responseDto = scheduleService.updateSchedule(id, requestDto);

        return new ResultDto<>(responseDto);
    }

    @DeleteMapping("/{id}")
    public Object deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);

        return new ResultDto<>();
    }
}
