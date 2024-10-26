package com.sparta.projectblue.domain.performer.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jakarta.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.common.enums.UserRole;
import com.sparta.projectblue.domain.performer.dto.*;
import com.sparta.projectblue.domain.performer.entity.Performer;
import com.sparta.projectblue.domain.performer.repository.PerformerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformerAdminService {

    private final PerformerRepository performerRepository;

    @Transactional
    public CreatePerformerResponseDto create(
            AuthUser authUser, @Valid CreatePerformerRequestDto request) {

        hasRole(authUser);

        // 생일관련 유효성검사
        try {
            LocalDate birthDate = LocalDate.parse(request.getBirth());
            if (birthDate.isBefore(LocalDate.of(1900, 1, 1))
                    || birthDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("생일은 현재 날짜보다 이전이거나 1900년도 이후의 날짜여야 합니다.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("생일은 yyyy-MM-dd 형식이어야 합니다.");
        }

        Performer performer =
                new Performer(request.getName(), request.getBirth(), request.getNation());
        performerRepository.save(performer);
        return new CreatePerformerResponseDto(performer);
    }

    @Transactional
    public UpdatePerformerResponseDto update(
            AuthUser authUser, Long id, @Valid UpdatePerformerRequestDto request) {

        hasRole(authUser);

        Performer performer =
                performerRepository
                        .findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("배우를 찾을 수 없습니다."));

        try {
            LocalDate birthDate = LocalDate.parse(request.getBirth());
            if (birthDate.isBefore(LocalDate.of(1900, 1, 1))
                    || birthDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("생일은 현재 날짜보다 이전이거나 1900년도 이후의 날짜여야 합니다.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("생일은 yyyy-MM-dd 형식이어야 합니다.");
        }

        performer.updateInfo(request.getName(), request.getBirth(), request.getNation());

        return new UpdatePerformerResponseDto(performer);
    }

    @Transactional
    public void delete(AuthUser authUser, Long id) {

        hasRole(authUser);

        Performer performer =
                performerRepository
                        .findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("해당 배우를 찾을 수 없습니다."));

        performerRepository.delete(performer);
    }

    public void hasRole(AuthUser authUser) {

        if (!authUser.hasRole(UserRole.ROLE_ADMIN)) {
            throw new IllegalArgumentException("관리자만 접근할 수 있습니다.");
        }
    }
}
