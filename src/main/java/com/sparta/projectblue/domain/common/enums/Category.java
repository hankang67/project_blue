package com.sparta.projectblue.domain.common.enums;

public enum Category {
    CONCERT("콘서트"),
    MUSICAL("뮤지컬"),
    SPORTS("스포츠");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
