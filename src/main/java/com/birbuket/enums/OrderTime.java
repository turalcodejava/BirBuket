package com.birbuket.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderTime {
    TIME1("09:00 - 12:00"),
    TIME2("12:00 - 15:00"),
    TIME3("15:00 - 18:00"),
    TIME4("18:00 - 21:00");

    private final String name;
}
