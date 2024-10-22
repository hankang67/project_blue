package com.sparta.projectblue.domain.poster.service;

import com.sparta.projectblue.domain.poster.repository.PosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PosterService {

    private final PosterRepository posterRepository;

}
