package com.sparta.projectblue.domain.es.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.sparta.projectblue.domain.es.document.SearchDocument;

public interface PerformanceEsRepository
        //        extends ElasticsearchRepository<PerformanceDocument, Long> {}
        extends ElasticsearchRepository<SearchDocument, Long> {

    List<SearchDocument> findByPerformanceTitleContainingOrPerformersPerformerIdInOrHallIdIn(
            String title, List<Long> performerIds, List<Long> hallIds);

    List<SearchDocument> findByPerformersPerformerIdIn(List<Long> performerIds);

    List<SearchDocument> findByHallIdIn(List<Long> hallIds);
}
