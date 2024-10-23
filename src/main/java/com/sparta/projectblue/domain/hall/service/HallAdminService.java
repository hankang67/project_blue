package com.sparta.projectblue.domain.hall.service;

import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.common.enums.UserRole;
import com.sparta.projectblue.domain.hall.dto.HallRequestDto;
import com.sparta.projectblue.domain.hall.entity.Hall;
import com.sparta.projectblue.domain.hall.repository.HallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HallAdminService {

    private final HallRepository hallRepository;

    @Transactional
    public Hall create(AuthUser authUser, HallRequestDto request) {
        hasRole(authUser);

        Hall hall = new Hall(request.getName(),
                request.getAddress(),
                request.getSeats());

        return hallRepository.save(hall);
    }

    @Transactional
    public Hall update(AuthUser authUser, Long id, HallRequestDto request) {
        hasRole(authUser);

        Hall hall = hallRepository.findByIdOrElseThrow(id);

        hall.update(request.getName(), request.getAddress(), request.getSeats());

        return hallRepository.save(hall);
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
