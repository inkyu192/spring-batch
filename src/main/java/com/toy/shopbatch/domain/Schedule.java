package com.toy.shopbatch.domain;

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
}
