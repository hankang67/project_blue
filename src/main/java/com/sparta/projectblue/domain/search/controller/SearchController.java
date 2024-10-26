package com.sparta.projectblue.domain.search.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    @Operation(summary = "공연리스트 필터 조회", description = "현재 진행중인 공연 리스트 조건에 따라 출력")
    public ResponseEntity<ApiResponse<?>> getFilterPerformances(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String performanceNm,
            @RequestParam(required = false) String userSelectDay,
            @RequestParam(required = false) String performer) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        searchService.searchPerformances(
                                page, size, performanceNm, userSelectDay, performer)));
    }
}
