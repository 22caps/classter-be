package com.syu.capsbe.domain.problem.application;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.member.application.MemberService;
import com.syu.capsbe.domain.problem.Problem;
import com.syu.capsbe.domain.problem.ProblemRepository;
import com.syu.capsbe.domain.problem.ProblemType;
import com.syu.capsbe.domain.problem.dto.request.ProblemHintRequestDto;
import com.syu.capsbe.domain.problem.dto.request.ProblemRequestDto;
import com.syu.capsbe.domain.problem.dto.response.ProblemHintResponseDto;
import com.syu.capsbe.domain.problem.dto.response.ProblemResponseDto;
import com.syu.capsbe.domain.problem.exception.ProblemExistsException;
import com.syu.capsbe.domain.problem.exception.common.ProblemErrorCode;
import com.syu.capsbe.domain.prompt.application.PromptService;
import com.syu.capsbe.domain.prompt.dto.response.PromptResponseDto;
import com.syu.capsbe.domain.solveHistory.application.SolveHistoryService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;
    private final SolveHistoryService solveHistoryService;
    private final MemberService memberService;
    private final PromptService promptService;

    @Override
    @Transactional
    public List<ProblemResponseDto> getProblemsByProblemType(Long memberId, ProblemRequestDto problemRequestDto) {
        // dto에서 type을 enum 값으로 맞게 변경
        ProblemType problemType = ProblemType.valueOf(problemRequestDto.getProblemType());

        // 문제 풀이를 시작한다는 뜻으로 solveCount를 1 증가시킴
        Member member = memberService.findByMemberId(memberId);
        member.updateSolveCount();

        // 껍데기 생성
        solveHistoryService.setSolveHistory(member, member.getSolveCount(), problemType);

        // dto에서 count를 가져와서 해당 type의 문제를 count만큼 가져옴
        return problemRepository.getProblemTypeIsWord(problemType,
                        problemRequestDto.getProblemCount())
                .stream()
                .map(ProblemResponseDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public ProblemHintResponseDto getHintByQuestion(ProblemHintRequestDto problemHintRequestDto) {
        Problem problem = getProblemById(problemHintRequestDto.getProblemId());

        PromptResponseDto promptResponse = promptService.getPromptResponse(problem.getQuestion());

        return ProblemHintResponseDto.of(promptResponse.getContent());
    }

    @Override
    public Problem getProblemById(Long problemId) {
        return problemRepository.findProblemById(problemId)
                .orElseThrow(() -> ProblemExistsException.of(ProblemErrorCode.PROBLEM_IS_NOT_EXISTS));
    }
}
