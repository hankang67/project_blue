package com.sparta.projectblue.domain.es.dto;

import java.util.List;

import com.sparta.projectblue.domain.es.document.SearchDocument;
import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.performer.entity.Performer;

import lombok.Getter;

@Getter
public class KeywordSearchResponseDto {
    private final List<Performer> performers;
    private final List<SearchDocument> searchDocuments;
    private final List<Hall> halls;

    public KeywordSearchResponseDto(
            List<Performer> performers, List<SearchDocument> searchDocuments, List<Hall> halls) {
        this.performers = performers;
        this.searchDocuments = searchDocuments;
        this.halls = halls;
    }
}
