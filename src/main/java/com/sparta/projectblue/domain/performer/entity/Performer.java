package com.sparta.projectblue.domain.performer.entity;

import com.sparta.projectblue.domain.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "performers")
public class Performer extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String name;

    private String birth;

    @Column(length = 50)
    private String nation;

    public Performer(String name, String birth, String nation) {
        this.name = name;
        this.birth = birth;
        this.nation = nation;
    }
}
