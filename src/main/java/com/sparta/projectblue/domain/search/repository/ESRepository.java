package com.sparta.projectblue.domain.search.repository;

import com.sparta.projectblue.domain.search.document.SearchDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ESRepository extends ElasticsearchRepository<SearchDocument, Long> {

    Page<SearchDocument> findByPerformanceTitleContainingOrPerformersPerformerIdInOrHallIdIn(
            String title, List<Long> performerIds, List<Long> hallIds, Pageable pageable);
}