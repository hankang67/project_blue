package com.sparta.projectblue.domain.performer.service;

import com.sparta.projectblue.domain.common.dto.AuthUser;
import com.sparta.projectblue.domain.common.enums.UserRole;
import com.sparta.projectblue.domain.performer.dto.CreatePerformerRequestDto;
import com.sparta.projectblue.domain.performer.dto.CreatePerformerResponseDto;
import com.sparta.projectblue.domain.performer.entity.Performer;
import com.sparta.projectblue.domain.performer.repository.PerformerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class PerformerAdminServiceTest {
    @Mock
    private PerformerRepository performerRepository;

    @InjectMocks
    private PerformerAdminService performerAdminService;

    @Test
    void 관리자_권한으로_배우를_생성한다() {
        // given
        AuthUser authUser = new AuthUser(1L, "admin@test.com", "adminUser", UserRole.ROLE_ADMIN);
        CreatePerformerRequestDto request = new CreatePerformerRequestDto("배두훈", "1986-07-15", "KOREA");

        Performer performer = new Performer(request.getName(), request.getBirth(), request.getNation());
        when(performerRepository.save(any(Performer.class))).thenReturn(performer);

        // when
        CreatePerformerResponseDto response = performerAdminService.create(authUser, request);

        // then
        assertNotNull(response);
        assertEquals("배두훈", response.getName());
    }
}
