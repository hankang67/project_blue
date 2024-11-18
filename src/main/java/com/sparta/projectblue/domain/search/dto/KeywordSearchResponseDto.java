package com.sparta.projectblue.domain.search.dto;

import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.performer.entity.Performer;
import com.sparta.projectblue.domain.search.document.SearchDocument;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class KeywordSearchResponseDto {
    private final Page<Performer> performers;
    private final Page<SearchDocument> searchDocuments;
    private final Page<Hall> halls;

    public KeywordSearchResponseDto(
            Page<Performer> performers, Page<SearchDocument> searchDocuments, Page<Hall> halls) {
        this.performers = performers;
        this.searchDocuments = searchDocuments;
        this.halls = halls;
    }
}