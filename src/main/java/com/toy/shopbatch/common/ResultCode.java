package com.toy.shopbatch.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResultCode {

    SUCCESS("200", "정상"),
    NOT_VALID("400", "요청 파라미터 오류"),
    ERROR("500", "시스템 오류가 발생하였습니다."),

    SCHEDULE_NOT_FOUND("S0001", "스케줄이 존재하지 않습니다.");

    private final String code;
    private final String message;
}
