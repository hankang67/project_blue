package com.sparta.projectblue.domain.performerperformance.service;

import com.sparta.projectblue.domain.performerperformance.repository.PerformerPerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformerPerformanceService {

    private final PerformerPerformanceRepository performerPerformanceRepository;
}
