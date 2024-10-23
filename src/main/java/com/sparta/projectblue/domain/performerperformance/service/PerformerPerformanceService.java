package com.sparta.projectblue.domain.performerperformance.service;

import com.sparta.projectblue.domain.performerperformance.repository.PerformerPerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformerPerformanceService {

    private final PerformerPerformanceRepository performerPerformanceRepository;
}
