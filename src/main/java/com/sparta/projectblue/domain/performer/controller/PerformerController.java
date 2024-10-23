package com.sparta.projectblue.domain.performer.controller;

import com.sparta.projectblue.domain.performer.dto.PerformerDetailDto;
import com.sparta.projectblue.domain.performer.service.PerformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/performers")
@RequiredArgsConstructor
public class PerformerController {

    private final PerformerService performerService;


}
