package com.toy.shopbatch.dto;

import com.toy.shopbatch.domain.Schedule;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

public class ScheduleDto {

    @Getter
    public static class Save {

        @NotEmpty
        private String jobName;

        @NotEmpty
        private String cron;

        @NotEmpty
        private String useYn;
    }

    @Getter
    public static class Update {

        @NotEmpty
        private String jobName;

        @NotEmpty
        private String cron;

        @NotEmpty
        private String useYn;
    }

    @Getter
    public static class Response {
        private final Long id;
        private final String jobName;
        private final String cron;
        private final String useYn;

        public Response(Schedule schedule) {
            this.id = schedule.getId();
            this.jobName = schedule.getJobName();
            this.cron = schedule.getCron();
            this.useYn = schedule.getUseYn();
        }
    }
}
