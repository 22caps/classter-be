package com.syu.capsbe.domain.problem.application;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.member.application.MemberService;
import com.syu.capsbe.domain.problem.Problem;
import com.syu.capsbe.domain.problem.ProblemRepository;
import com.syu.capsbe.domain.problem.ProblemType;
import com.syu.capsbe.domain.problem.dto.response.ProblemHintResponseDto;
import com.syu.capsbe.domain.problem.dto.response.ProblemResponseDto;
import com.syu.capsbe.domain.problem.exception.ProblemExistsException;
import com.syu.capsbe.domain.problem.exception.common.ProblemErrorCode;
import com.syu.capsbe.domain.prompt.application.PromptService;
import com.syu.capsbe.domain.prompt.dto.response.PromptResponseDto;
import com.syu.capsbe.domain.solveHistory.application.SolveHistoryService;
import java.util.List;
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
    public List<ProblemResponseDto> getProblemsByProblemType(Long memberId,
            String problemTypeRequest, int problemCountRequest) {
        ProblemType problemType = ProblemType.valueOf(problemTypeRequest);

        Member member = memberService.findByMemberId(memberId);
        member.updateSolveCount();

        solveHistoryService.setSolveHistory(member, member.getSolveCount(), problemType);

        return problemRepository.getProblemTypeIsWord(problemType,
                        problemCountRequest)
                .stream()
                .map(ProblemResponseDto::of)
                .toList();
    }

    @Override
    public ProblemHintResponseDto getHintByQuestion(Long problemId) {
        Problem problem = getProblemById(problemId);

        PromptResponseDto promptResponse = promptService.getPromptResponse(problem.getQuestion());

        return ProblemHintResponseDto.of(promptResponse.getContent());
    }

    @Override
    public Problem getProblemById(Long problemId) {
        return problemRepository.findProblemById(problemId)
                .orElseThrow(
                        () -> ProblemExistsException.of(ProblemErrorCode.PROBLEM_IS_NOT_EXISTS));
    }
}
