package com.sparta.projectblue.domain.es.service;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import com.sparta.projectblue.domain.es.document.PerformanceDocument;

@Service
public class PerformanceSearchService {

    @Autowired private ElasticsearchOperations elasticsearchOperations;

    // 다양한 검색 조건을 처리하도록 수정
    public List<PerformanceDocument> searchPerformances(
            String title,
            String description,
            String actorName,
            LocalDateTime startDate,
            LocalDateTime endDate) {

        Criteria criteria = new Criteria();

        if (title != null && !title.isEmpty()) {
            criteria.and(new Criteria("title").matches(title));
        }

        if (description != null && !description.isEmpty()) {
            criteria.and(new Criteria("description").matches(description));
        }

        if (actorName != null && !actorName.isEmpty()) {
            criteria.and(new Criteria("actorNames").matches(actorName)); // 예시로 추가한 배우 이름 필드
        }

        if (startDate != null && endDate != null) {
            criteria.and(
                    new Criteria("startDate")
                            .greaterThanEqual(startDate)
                            .and(new Criteria("endDate").lessThanEqual(endDate)));
        }

        CriteriaQuery query = new CriteriaQuery(criteria);

        SearchHits<PerformanceDocument> searchHits =
                elasticsearchOperations.search(query, PerformanceDocument.class);

        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }
}
