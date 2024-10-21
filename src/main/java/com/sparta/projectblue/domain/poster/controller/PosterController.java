package com.sparta.projectblue.domain.poster.controller;

import com.sparta.projectblue.domain.poster.service.PosterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posters")
@RequiredArgsConstructor
public class PosterController {

    private final PosterService posterService;
}
