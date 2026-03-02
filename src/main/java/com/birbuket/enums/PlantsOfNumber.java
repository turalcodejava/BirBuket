package com.birbuket.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlantsOfNumber {

    F1_3("1-3", 10),
    F4_7("4-7", 15),
    F8("8+", 20);

    private final String name;
    private final double price;
}
