package com.sparta.projectblue.domain.hall.service;


import com.sparta.projectblue.domain.hall.dto.HallResponseDto;
import com.sparta.projectblue.domain.hall.dto.HallsResponseDto;
import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HallService {

    private final HallRepository hallRepository;

    public List<HallsResponseDto> getHalls() {
        List<Hall> halls = hallRepository.findAll();

        return halls.stream().map(hall -> new HallsResponseDto(hall.getName(), hall.getSeats()))
                .collect(Collectors.toList());

    }

    public HallResponseDto getHall(Long id) {
        Hall hall = hallRepository.findByIdOrElseThrow(id);

        return new HallResponseDto(hall.getId(), hall.getName(), hall.getAddress(), hall.getSeats());
    }
}
