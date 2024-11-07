package com.sparta.projectblue.domain.search.service;

import com.sparta.projectblue.domain.reservation.repository.ReservationRepository;
import com.sparta.projectblue.domain.search.document.UserBookingDocument;
import com.sparta.projectblue.domain.search.dto.UserBookingDto;
import com.sparta.projectblue.domain.search.repository.UserBookingEsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserBookingSearchServiceTest {
    @Mock
    private UserBookingEsRepository userBookingEsRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private UserBookingSearchService userBookingSearchService;

    @Test
    void 데이터_동기화시_Elasticsearch에_모든_문서를_저장해야한다() {
        // given
        UserBookingDto dto = new UserBookingDto();
        when(reservationRepository.findUserBookingData()).thenReturn(List.of(dto));

        // when
        userBookingSearchService.syncData();

        // then
        verify(userBookingEsRepository).saveAll(anyList());
    }

    @Test
    void 예약_검색시_Elasticsearch에서_결과를_반환해야한다() {
        // given
        UserBookingDto searchCriteria = new UserBookingDto();
        UserBookingDocument document = new UserBookingDocument();
        // Populate searchCriteria and document with necessary test data
        when(userBookingEsRepository.searchByCriteria(searchCriteria)).thenReturn(List.of(document));

        // when
        List<UserBookingDocument> results = userBookingSearchService.searchBookings(searchCriteria);

        // then
        assertEquals(1, results.size());
        assertEquals(document, results.get(0));
    }

}
