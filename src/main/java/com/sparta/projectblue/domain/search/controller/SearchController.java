package com.sparta.projectblue.domain.search.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sparta.projectblue.config.ApiResponse;
import com.sparta.projectblue.domain.search.service.SearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
@Tag(name = "Search", description = "검색 API")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/filter")
    @Operation(summary = "공연리스트 필터 조회", description = "현재 진행중인 공연 리스트 조건에 따라 출력")
    public ResponseEntity<ApiResponse<?>> getFilterPerformances(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String performanceNm,
            @RequestParam(required = false) String userSelectDay,
            @RequestParam(required = false) String performer) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        searchService.filterSearch(
                                page, size, performanceNm, userSelectDay, performer)));
    }

    @GetMapping("/keyword")
    @Operation(summary = "키워드검색", description = "키워드를 검색하면 배우, 공연장, 공연에 대한 결과가 조회됩니다")
    public ResponseEntity<ApiResponse<?>> search(@RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(ApiResponse.success(searchService.search(keyword)));
    }

    @PostMapping("/sync")
    @Operation(summary = "기존 데이터를 동기화", description = "기존 MySQL 데이터베이스의 모든 데이터를 Elasticsearch로 동기화")
    public ResponseEntity<ApiResponse<?>> syncAllPerformances() {
        return ResponseEntity.ok(ApiResponse.success(searchService.syncDocument()));
    }
}
