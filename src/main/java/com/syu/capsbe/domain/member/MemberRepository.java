package com.syu.capsbe.domain.member;

import com.syu.capsbe.domain.member.vo.EmailVo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.roles WHERE m.email = :email")
    Optional<Member> findByEmail(EmailVo email);

    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.roles WHERE m.uuid = :uuid")
    Optional<Member> findByUuid(String uuid);

    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.roles WHERE m.email = :email AND m.deletedAt IS NULL")
    Optional<Member> findByEmailAndDeletedAtIsNull(EmailVo email);
}
