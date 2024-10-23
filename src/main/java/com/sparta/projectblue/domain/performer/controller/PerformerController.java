package com.sparta.projectblue.domain.performer.controller;

import com.sparta.projectblue.domain.performer.service.PerformerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performers")
@RequiredArgsConstructor
@Tag(name = "Performer", description = "출연자 관리 API")
public class PerformerController {

    private final PerformerService performerService;
}
