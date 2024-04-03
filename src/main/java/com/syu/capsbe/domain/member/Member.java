package com.syu.capsbe.domain.member;

import com.syu.capsbe.domain.member.vo.EmailVo;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private EmailVo email;

    @Column(nullable = false)
    private String uuid;

    @Builder
    public Member(String email) {
        this.email = new EmailVo(email);
        this.uuid = UUID.randomUUID().toString();
    }
}
