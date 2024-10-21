package com.sparta.projectblue.domain.poster.service;

import com.sparta.projectblue.domain.poster.repository.PosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PosterService {

    private final PosterRepository posterRepository;
}
