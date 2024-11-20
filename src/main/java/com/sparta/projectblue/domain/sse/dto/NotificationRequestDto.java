package com.sparta.projectblue.domain.sse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestDto {

    private String userId;
    private String title;
    private String message;
}