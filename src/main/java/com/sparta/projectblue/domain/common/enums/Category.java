package com.sparta.projectblue.domain.common.enums;

import lombok.Getter;

@Getter
public enum Category {
    CONCERT("콘서트"),
    MUSICAL("뮤지컬"),
    SPORTS("스포츠");

    private final String value;

    Category(String value) {

        this.value = value;
    }
}
