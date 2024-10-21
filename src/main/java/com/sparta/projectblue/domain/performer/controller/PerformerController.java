package com.sparta.projectblue.domain.performer.controller;

import com.sparta.projectblue.domain.performer.service.PerformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performers")
@RequiredArgsConstructor
public class PerformerController {

    private final PerformerService performerService;
}
