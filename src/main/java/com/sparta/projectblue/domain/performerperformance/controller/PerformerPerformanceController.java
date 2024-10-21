package com.sparta.projectblue.domain.performerperformance.controller;

import com.sparta.projectblue.domain.performerperformance.service.PerformerPerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performers-performances")
@RequiredArgsConstructor
public class PerformerPerformanceController {

    private final PerformerPerformanceService performerPerformanceService;
}
