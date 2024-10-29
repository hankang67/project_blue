package com.sparta.projectblue.domain.es.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.es.service.PerformanceSearchService;
import com.sparta.projectblue.domain.es.service.PerformanceSyncService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
@Tag(name = "Search", description = "검색 API")
public class PerformanceSearchController {

    private final PerformanceSearchService searchService;
    private final PerformanceSyncService syncService;

    //    @GetMapping("/performances")
    //    @Operation(summary = "키워드로 검색 가능", description = "공연 제목, 설명, 배우 이름, 시작 날짜, 종료 날짜 조건으로 검색")
    //    public List<PerformanceDocument> searchPerformances(
    //            @RequestParam(required = false) String title,
    //            @RequestParam(required = false) String description,
    //            @RequestParam(required = false) String actorName,
    //            @RequestParam(required = false) @DateTimeFormat(iso =
    // DateTimeFormat.ISO.DATE_TIME)
    //                    LocalDateTime startDate,
    //            @RequestParam(required = false) @DateTimeFormat(iso =
    // DateTimeFormat.ISO.DATE_TIME)
    //                    LocalDateTime endDate) {
    //
    //        return searchService.searchPerformances(title, description, actorName, startDate,
    // endDate);
    //    }

    @PostMapping("/sync")
    @Operation(summary = "기존 데이터를 동기화", description = "기존 MySQL 데이터베이스의 모든 데이터를 Elasticsearch로 동기화")
    public ResponseEntity<ApiResponse<?>> syncAllPerformances() {
        //        syncService.syncDocument();
        //        return ResponseEntity.ok(ApiResponse.success("Elasticsearch에 추가 완료되었습니다."));
        return ResponseEntity.ok(ApiResponse.success(syncService.syncDocument()));
    }

    @GetMapping("/keyword")
    @Operation(summary = "키워드검색", description = "키워드를 검색하면 배우, 공연장, 공연에 대한 결과가 조회됩니다")
    public ResponseEntity<ApiResponse<?>> search(@RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(ApiResponse.success(searchService.search(keyword)));
    }
}
