package com.sparta.projectblue.domain.hall.service;

import com.sparta.projectblue.domain.hall.repository.HallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HallAdminService {

    private final HallRepository hallRepository;

}
