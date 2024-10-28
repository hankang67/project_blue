package com.sparta.projectblue.domain.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.sparta.projectblue.domain.es.document.PerformanceDocument;

public interface PerformanceEsRepository
        extends ElasticsearchRepository<PerformanceDocument, Long> {}
