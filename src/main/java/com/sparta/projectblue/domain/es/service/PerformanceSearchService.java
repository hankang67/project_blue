package com.sparta.projectblue.domain.es.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.sparta.projectblue.domain.es.document.SearchDocument;
import com.sparta.projectblue.domain.es.dto.KeywordSearchResponseDto;
import com.sparta.projectblue.domain.es.repository.PerformanceEsRepository;
import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import com.sparta.projectblue.domain.performer.entity.Performer;
import com.sparta.projectblue.domain.performer.repository.PerformerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PerformanceSearchService {

    private final PerformanceEsRepository performanceEsRepository;

    private final HallRepository hallRepository;
    private final PerformerRepository performerRepository;

    //    private final ElasticsearchOperations elasticsearchOperations;
    // 다양한 검색 조건을 처리하도록 수정
    //    public List<PerformanceDocument> searchPerformances(
    //            String title,
    //            String description,
    //            String actorName,
    //            LocalDateTime startDate,
    //            LocalDateTime endDate) {
    //
    //        Criteria criteria = new Criteria();
    //
    //        if (title != null && !title.isEmpty()) {
    //            criteria.and(new Criteria("title").matches(title));
    //        }
    //
    //        if (description != null && !description.isEmpty()) {
    //            criteria.and(new Criteria("description").matches(description));
    //        }
    //
    //        if (actorName != null && !actorName.isEmpty()) {
    //            criteria.and(new Criteria("actorNames").matches(actorName)); // 예시로 추가한 배우 이름 필드
    //        }
    //
    //        if (startDate != null && endDate != null) {
    //            criteria.and(
    //                    new Criteria("startDate")
    //                            .greaterThanEqual(startDate)
    //                            .and(new Criteria("endDate").lessThanEqual(endDate)));
    //        }
    //
    //        CriteriaQuery query = new CriteriaQuery(criteria);
    //
    //        SearchHits<PerformanceDocument> searchHits =
    //                elasticsearchOperations.search(query, PerformanceDocument.class);
    //
    //        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    //    }

    public KeywordSearchResponseDto search(String keyword) {

        if (Objects.isNull(keyword) || keyword.trim().isEmpty()) {
            return null;
        }

        List<Performer> performers = performerRepository.findAllByName(keyword);

        List<Long> performerIds = performers.stream().map(Performer::getId).toList();

        List<Hall> halls = hallRepository.findByNameContaining(keyword);

        List<Long> hallIds = halls.stream().map(Hall::getId).toList();

        List<SearchDocument> searchDocuments =
                performanceEsRepository
                        .findByPerformanceTitleContainingOrPerformersPerformerIdInOrHallIdIn(
                                keyword, performerIds, hallIds);

        return new KeywordSearchResponseDto(performers, searchDocuments, halls);
    }
}
