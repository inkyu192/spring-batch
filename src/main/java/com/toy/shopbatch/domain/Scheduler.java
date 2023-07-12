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
public class Scheduler extends BaseDomain {

    @Id
    @GeneratedValue
    @Column(name = "scheduler_id")
    private Long id;
    private String name;
    private String useYn;
    private String cron;
}
