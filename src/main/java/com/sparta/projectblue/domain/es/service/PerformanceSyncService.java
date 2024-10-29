package com.sparta.projectblue.domain.es.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sparta.projectblue.domain.es.document.SearchDocument;
import com.sparta.projectblue.domain.es.repository.PerformanceEsRepository;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PerformanceSyncService {

    private final PerformanceRepository performanceRepository;

    private final PerformanceEsRepository elasticsearchRepository;

    //    public Performance syncPerformance(Performance performance) {
    //        // 1. MySQL에 데이터 저장
    //        Performance savedPerformance = performanceRepository.save(performance);
    //
    //        // 2. Elasticsearch에 인덱싱
    //        PerformanceDocument document = convertToDocument(savedPerformance);
    //        elasticsearchRepository.save(document); // Elasticsearch에 인덱싱
    //
    //        return savedPerformance;
    //    }

    // 기존 데이터를 Elasticsearch로 동기화하는 메서드

    //    public void syncAllPerformances() {
    //        List<Performance> allPerformances = performanceRepository.findAll();
    //        List<PerformanceDocument> documents =
    //
    // allPerformances.stream().map(this::convertToDocument).collect(Collectors.toList());
    //        elasticsearchRepository.saveAll(documents);
    //    }

    public List<SearchDocument> syncDocument() {
        List<SearchDocument> documents = performanceRepository.findForESDocument();
        elasticsearchRepository.saveAll(documents);
        return documents;
    }

    //    private PerformanceDocument convertToDocument(Performance performance) {
    //        return new PerformanceDocument(
    //                performance.getId(),
    //                performance.getTitle(),
    //                performance.getDescription(),
    //                performance.getCategory(),
    //                performance.getDuration(),
    //                performance.getPrice(),
    //                performance.getStartDate().toEpochSecond(ZoneOffset.UTC) * 1000,
    //                performance.getEndDate().toEpochSecond(ZoneOffset.UTC) * 1000,
    //                performance.getHallId());
    //    }
}
