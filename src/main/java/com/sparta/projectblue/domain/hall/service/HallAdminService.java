package com.sparta.projectblue.domain.hall.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.common.enums.UserRole;
import com.sparta.projectblue.domain.hall.dto.CreateHallRequestDto;
import com.sparta.projectblue.domain.hall.dto.CreateHallResponseDto;
import com.sparta.projectblue.domain.hall.dto.UpdateHallRequestDto;
import com.sparta.projectblue.domain.hall.dto.UpdateHallResponseDto;
import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.hall.repository.HallRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HallAdminService {

    private final HallRepository hallRepository;

    @Transactional
    public CreateHallResponseDto create(AuthUser authUser, CreateHallRequestDto request) {
        hasRole(authUser);

        Hall hall = new Hall(request.getName(), request.getAddress(), request.getSeats());

        hallRepository.save(hall);

        return new CreateHallResponseDto(hall);
    }

    @Transactional
    public UpdateHallResponseDto update(AuthUser authUser, Long id, UpdateHallRequestDto request) {
        hasRole(authUser);

        Hall hall = hallRepository.findByIdOrElseThrow(id);

        hall.update(request.getName(), request.getAddress(), request.getSeats());

        return new UpdateHallResponseDto(hall);
    }

    @Transactional
    public void delete(AuthUser authUser, Long id) {
        hasRole(authUser);

        Hall hall = hallRepository.findByIdOrElseThrow(id);

        hallRepository.delete(hall);
    }

    public void hasRole(AuthUser authUser) {
        if (!authUser.hasRole(UserRole.ROLE_ADMIN)) {
            throw new IllegalArgumentException("관리자만 접근할 수 있습니다.");
        }
    }
}
