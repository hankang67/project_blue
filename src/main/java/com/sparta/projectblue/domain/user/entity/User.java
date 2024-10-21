package com.sparta.projectblue.domain.user.entity;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import com.sparta.projectblue.domain.common.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private boolean isDeleted;

    public User(String email, String name, String password, UserRole userRole) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.userRole = userRole;
    }

    public void changeIsDeleted() {
        this.isDeleted = true; // 탈퇴 처리
    }
}