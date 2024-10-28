package com.sparta.projectblue.domain.es.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparta.projectblue.domain.es.document.PerformanceDocument;
import com.sparta.projectblue.domain.es.repository.PerformanceEsRepository;
import com.sparta.projectblue.domain.performance.entity.Performance;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;

@Service
public class PerformanceSyncService {

    private final PerformanceRepository performanceRepository;
    private final PerformanceEsRepository elasticsearchRepository;

    @Autowired
    public PerformanceSyncService(
            PerformanceRepository performanceRepository,
            PerformanceEsRepository elasticsearchRepository) {
        this.performanceRepository = performanceRepository;
        this.elasticsearchRepository = elasticsearchRepository;
    }

    public Performance syncPerformance(Performance performance) {
        // 1. MySQL에 데이터 저장
        Performance savedPerformance = performanceRepository.save(performance);

        // 2. Elasticsearch에 인덱싱
        PerformanceDocument document = convertToDocument(savedPerformance);
        elasticsearchRepository.save(document); // Elasticsearch에 인덱싱

        return savedPerformance;
    }

    // 기존 데이터를 Elasticsearch로 동기화하는 메서드
    public void syncAllPerformances() {
        List<Performance> allPerformances = performanceRepository.findAll();
        List<PerformanceDocument> documents =
                allPerformances.stream().map(this::convertToDocument).collect(Collectors.toList());

        elasticsearchRepository.saveAll(documents); // Elasticsearch에 동기화
    }

    private PerformanceDocument convertToDocument(Performance performance) {
        return new PerformanceDocument(
                performance.getId(),
                performance.getTitle(),
                performance.getDescription(),
                performance.getCategory(),
                performance.getDuration(),
                performance.getPrice(),
                performance.getStartDate(),
                performance.getEndDate(),
                performance.getHallId());
    }
}
