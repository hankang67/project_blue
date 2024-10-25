package com.sparta.projectblue.domain.hall.service;


import com.sparta.projectblue.domain.hall.dto.HallResponseDto;
import com.sparta.projectblue.domain.hall.dto.HallsResponseDto;
import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HallService {

    private final HallRepository hallRepository;

    public Page<HallsResponseDto> getHalls(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);

        return hallRepository.findAll(pageable).map(hall -> new HallsResponseDto(hall.getName(),
                hall.getSeats()));
    }

    public HallResponseDto getHall(Long id) {
        Hall hall = hallRepository.findByIdOrElseThrow(id);

        return new HallResponseDto(hall.getId(), hall.getName(), hall.getAddress(), hall.getSeats());
    }
}