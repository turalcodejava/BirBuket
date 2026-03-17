package com.birbuket.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND("AUTH001"),
    USER_IS_NOT_ACTIVE("AUTH002"),
    USER_ALREADY_EXISTS("AUTH003"),
    PASSWORD_MISMATCH("AUTH004");

    private final String code;

}
