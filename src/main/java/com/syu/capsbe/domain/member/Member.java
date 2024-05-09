package com.syu.capsbe.domain.member;

import com.syu.capsbe.domain.member.exception.InvalidGoalScoreException;
import com.syu.capsbe.domain.member.exception.common.MemberErrorCode;
import com.syu.capsbe.domain.member.vo.EmailVo;
import com.syu.capsbe.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private EmailVo email;

    @Column(nullable = false)
    private String uuid;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> roles;

    @Column(nullable = false)
    private int goalScore;

    @Builder
    public Member(String email) {
        this.email = new EmailVo(email);
        this.uuid = UUID.randomUUID().toString();
        this.roles = List.of("ROLE_USER");
        this.goalScore = 0;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int updateGoalScore(int goalScore) {
        validateGoalScore(goalScore);
        this.goalScore = goalScore;
        return this.goalScore;
    }

    private void validateGoalScore(int goalScore) {
        if (goalScore <= 100 || goalScore > 990) {
            throw InvalidGoalScoreException.of(MemberErrorCode.INVALID_GOAL_SCORE);
        }
    }
}
