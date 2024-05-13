package com.syu.capsbe.domain.member;

import com.syu.capsbe.domain.member.vo.EmailVo;
import java.util.List;
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

    @Query(value =
            "SELECT \n"
                    + "    ROUND(SUM(CASE WHEN shd.is_correct = TRUE THEN 1 ELSE 0 END) * 100.0 / COUNT(shd.id), 2) AS accuracyRate,\n"
                    + "    DATE(CONVERT_TZ(sh.solve_date, '+00:00', '+09:00')) AS solveDate,\n"
                    + "    DAYNAME(MIN(CONVERT_TZ(sh.solve_date, '+00:00', '+09:00'))) AS dayOfWeek\n"
                    + "FROM \n"
                    + "    solve_histories sh\n"
                    + "JOIN \n"
                    + "    solve_history_details shd ON sh.id = shd.solve_history_id\n"
                    + "WHERE \n"
                    + "    sh.member_id = :memberId\n"
                    + "    AND sh.is_completed = TRUE\n"
                    + "    AND sh.solve_date >= CONVERT_TZ(DATE_SUB(CURDATE(), INTERVAL 7 DAY), '+09:00', '+00:00')\n"
                    + "GROUP BY \n"
                    + "    DATE(CONVERT_TZ(sh.solve_date, '+00:00', '+09:00'))",
            nativeQuery = true)
    List<Object[]> findWeeklyPercentCorrect(Long memberId);
}
