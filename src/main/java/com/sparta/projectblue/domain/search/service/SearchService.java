package com.sparta.projectblue.domain.search.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import com.sparta.projectblue.domain.performance.dto.GetPerformancesResponseDto;
import com.sparta.projectblue.domain.performance.repository.PerformanceRepository;
import com.sparta.projectblue.domain.performer.entity.Performer;
import com.sparta.projectblue.domain.performer.repository.PerformerRepository;
import com.sparta.projectblue.domain.search.document.SearchDocument;
import com.sparta.projectblue.domain.search.dto.KeywordSearchResponseDto;
import com.sparta.projectblue.domain.search.repository.ESRepository;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    private final PerformanceRepository performanceRepository;
    private final ESRepository elasticsearchRepository;

    private final HallRepository hallRepository;
    private final PerformerRepository performerRepository;

    public Page<GetPerformancesResponseDto> searchFilter(
            int page, int size, String performanceNm, String userSelectDay, String performer) {

        Pageable pageable = PageRequest.of(page - 1, size);

        LocalDateTime performanceDay =
                (userSelectDay != null)
                        ? LocalDate.parse(userSelectDay).atTime(LocalTime.NOON)
                        : LocalDateTime.now();

        return performanceRepository.findByCondition(
                pageable, performanceNm, performanceDay, performer);
    }

    public KeywordSearchResponseDto searchKeyword(String keyword) {

        if (Objects.isNull(keyword) || keyword.trim().isEmpty()) {
            return null;
        }

        List<Performer> performers = performerRepository.findAllByName(keyword);

        List<Long> performerIds = performers.stream().map(Performer::getId).toList();

        List<Hall> halls = hallRepository.findByNameContaining(keyword);

        List<Long> hallIds = halls.stream().map(Hall::getId).toList();

        List<SearchDocument> searchDocuments =
                elasticsearchRepository
                        .findByPerformanceTitleContainingOrPerformersPerformerIdInOrHallIdIn(
                                keyword, performerIds, hallIds);

        return new KeywordSearchResponseDto(performers, searchDocuments, halls);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void syncDocumentScheduled() {
        try {
            List<SearchDocument> documents = performanceRepository.findForESDocument();
            elasticsearchRepository.saveAll(documents);
            log.info("Elasticsearch sync completed successfully every 10 minutes with {} documents synced.", documents.size());
        } catch (Exception e) {
            log.error("Elasticsearch sync failed", e);
        }
    }

}