package com.birbuket.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryHours {

    FIRST("09:00-12:00"),
    SECOND("12:00-15:00"),
    THIRD("15:00-18:00"),
    FOURTH("18:00-21:00"),
    FIFTH("21:00-00:00"),
    SIXTH("00:00-03:00"),
    SEVENTH("03:00-06:00"),
    EIGHTH("06:00-09:00");

    private final String hours;
}
