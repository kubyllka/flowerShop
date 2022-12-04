package com.kbilyk.constant;

import java.util.Arrays;
import java.util.Optional;

public enum OrderSort {

    DESC ("DESC", "In descending order"),

    ASC ("ASC", "In ascending order" );

    private final String enumType;    // the number of that type
    private final String name;    // the name of order type

    OrderSort(String enumType, String name) {
        this.enumType = enumType;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Get the enum name of accessory type
     * @return The enum name of accessory type
     */
    public String getEnumType() {
        return enumType;
    }
}
