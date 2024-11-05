package com.syu.capsbe.domain.member.application;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.member.MemberRepository;
import com.syu.capsbe.domain.member.dto.request.MemberUpdateRequestDto;
import com.syu.capsbe.domain.member.dto.response.CorrectResponseDto;
import com.syu.capsbe.domain.member.dto.response.MemberInfoResponseDto;
import com.syu.capsbe.domain.member.exception.MemberExistsException;
import com.syu.capsbe.domain.member.exception.common.MemberErrorCode;
import com.syu.capsbe.domain.member.vo.EmailVo;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long save(Member member) {
        return memberRepository.save(member).getId();
    }

    @Override
    public void checkMemberIsExist(String email) {
        if (memberRepository.findByEmail(new EmailVo(email)).isPresent()) {
            throw MemberExistsException.of(MemberErrorCode.MEMBER_IS_ALREADY_EXIST);
        }
    }

    @Override
    public Member findByMemberId(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> MemberExistsException.of(MemberErrorCode.MEMBER_IS_NOT_EXIST));
    }

    @Override
    public Member findByUuid(String uuid) {
        return memberRepository.findByUuid(uuid)
                .orElseThrow(() -> MemberExistsException.of(MemberErrorCode.MEMBER_IS_NOT_EXIST));
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.ofNullable(memberRepository.findByEmail(new EmailVo(email))
                .orElseThrow(() -> MemberExistsException.of(MemberErrorCode.MEMBER_IS_NOT_EXIST)));
    }

    @Override
    public MemberInfoResponseDto getMemberInfo(Long memberId) {
        Member member = findByMemberId(memberId);
        return MemberInfoResponseDto.of(member.getEmail().getEmail(), member.getGoalScore());
    }

    @Override
    @Transactional
    public MemberInfoResponseDto updateMember(Long memberId,
            MemberUpdateRequestDto memberUpdateRequestDto) {
        Member member = findByMemberId(memberId);
        member.updateGoalScore(memberUpdateRequestDto.getGoalScore());
        return MemberInfoResponseDto.of(member.getEmail().getEmail(), member.getGoalScore());
    }

    @Override
    public List<CorrectResponseDto> getWeeklyPercentCorrect(Long memberId) {
        ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");
        LocalDate now = LocalDate.now(koreaZoneId);
        Map<LocalDate, CorrectResponseDto> rateMap = new HashMap<>();
        for (int i = 0; i < 7; i++) {
            LocalDate date = now.minusDays(i);
            rateMap.put(date,
                    new CorrectResponseDto(date, "0.00"));
        }

        List<Object[]> results = memberRepository.findWeeklyPercentCorrect(memberId);
        for (Object[] result : results) {
            Double accuracyRate = Double.parseDouble(String.valueOf(result[0]));
            Date solveDate = (Date) result[1];
            LocalDate localSolveDate = LocalDate.parse(solveDate.toString());

            if (rateMap.containsKey(localSolveDate)) {
                rateMap.get(localSolveDate).setAccuracyRate(String.format("%.2f", accuracyRate));
            }
        }

        return rateMap.values().stream()
                .sorted(Comparator.comparing(CorrectResponseDto::getSolveDate))
                .collect(Collectors.toList());
    }
}


