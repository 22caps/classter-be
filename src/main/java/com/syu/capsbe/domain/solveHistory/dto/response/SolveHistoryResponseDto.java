package com.syu.capsbe.domain.solveHistory.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.syu.capsbe.domain.problem.ProblemType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "문제 풀이 전체 기록 응답 정보")
public class SolveHistoryResponseDto {

    @Schema(description = "풀이 기록 번호 | 문제 리스트 번호", example = "3")
    private Long solveHistoryId;

    @Schema(description = "문제 유형", example = "GRAMMAR")
    private ProblemType problemType;

    @Schema(description = "정답률", example = "33.0")
    private double correctRate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @Schema(description = "풀이 날짜", example = "2024-05-29T00:00:00")
    private LocalDateTime solveDate;

    @Schema(description = "복습 여부", example = "false")
    private boolean isReviewed;

    @Schema(description = "풀이 세부 기록", example = "{\n"
            + "            \"solveHistoryId\": 19,\n"
            + "            \"solveHistoryDetail\": [\n"
            + "                {\n"
            + "                    \"question\": \"Fill in the blank: 'The contract was deemed invalid because it was not ______ signed by all parties.'\",\n"
            + "                    \"choices\": [\n"
            + "                        \"a) legally\",\n"
            + "                        \"b) optionally\",\n"
            + "                        \"c) forcefully\",\n"
            + "                        \"d) barely\"\n"
            + "                    ],\n"
            + "                    \"answer\": \"a\",\n"
            + "                    \"userAnswer\": \"a\",\n"
            + "                    \"correct\": true\n"
            + "                },\n"
            + "                {\n"
            + "                    \"question\": \"What is the meaning of 'streamline' in the following sentence? 'The company decided to streamline its operations to improve efficiency.'\",\n"
            + "                    \"choices\": [\n"
            + "                        \"a) expand\",\n"
            + "                        \"b) simplify\",\n"
            + "                        \"c) complicate\",\n"
            + "                        \"d) reduce\"\n"
            + "                    ],\n"
            + "                    \"answer\": \"b\",\n"
            + "                    \"userAnswer\": \"a\",\n"
            + "                    \"correct\": false\n"
            + "                }\n"
            + "            ]\n"
            + "        },\n"
            + "        {\n"
            + "            \"solveHistoryId\": 20,\n"
            + "            \"solveHistoryDetail\": [\n"
            + "                {\n"
            + "                    \"question\": \"Choose the correct word to fill in the blank. 'Despite numerous attempts, the treaty has yet to be ___.'\",\n"
            + "                    \"choices\": [\n"
            + "                        \"a) ratified\",\n"
            + "                        \"b) denounced\",\n"
            + "                        \"c) revoked\",\n"
            + "                        \"d) escalated\"\n"
            + "                    ],\n"
            + "                    \"answer\": \"a\",\n"
            + "                    \"userAnswer\": \"a\",\n"
            + "                    \"correct\": true\n"
            + "                },\n"
            + "                {\n"
            + "                    \"question\": \"What is the antonym of 'innovative'?\",\n"
            + "                    \"choices\": [\n"
            + "                        \"a) modern\",\n"
            + "                        \"b) traditional\",\n"
            + "                        \"c) recent\",\n"
            + "                        \"d) advanced\"\n"
            + "                    ],\n"
            + "                    \"answer\": \"b\",\n"
            + "                    \"userAnswer\": \"a\",\n"
            + "                    \"correct\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"question\": \"Choose the word that best completes the sentence. 'The manager needs to ___ the project before any further actions are taken.'\",\n"
            + "                    \"choices\": [\n"
            + "                        \"a) commence\",\n"
            + "                        \"b) submit\",\n"
            + "                        \"c) approve\",\n"
            + "                        \"d) decline\"\n"
            + "                    ],\n"
            + "                    \"answer\": \"c\",\n"
            + "                    \"userAnswer\": \"a\",\n"
            + "                    \"correct\": false\n"
            + "                }\n"
            + "            ]\n"
            + "        },\n"
            + "        {\n"
            + "            \"solveHistoryId\": 24,\n"
            + "            \"solveHistoryDetail\": [\n"
            + "                {\n"
            + "                    \"question\": \"Choose the word that best completes the sentence. 'The manager needs to ___ the project before any further actions are taken.'\",\n"
            + "                    \"choices\": [\n"
            + "                        \"a) commence\",\n"
            + "                        \"b) submit\",\n"
            + "                        \"c) approve\",\n"
            + "                        \"d) decline\"\n"
            + "                    ],\n"
            + "                    \"answer\": \"c\",\n"
            + "                    \"userAnswer\": \"a\",\n"
            + "                    \"correct\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"question\": \"Choose the word that best completes the sentence. 'The manager needs to ___ the project before any further actions are taken.'\",\n"
            + "                    \"choices\": [\n"
            + "                        \"a) commence\",\n"
            + "                        \"b) submit\",\n"
            + "                        \"c) approve\",\n"
            + "                        \"d) decline\"\n"
            + "                    ],\n"
            + "                    \"answer\": \"c\",\n"
            + "                    \"userAnswer\": \"a\",\n"
            + "                    \"correct\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"question\": \"Choose the word that best completes the sentence. 'The manager needs to ___ the project before any further actions are taken.'\",\n"
            + "                    \"choices\": [\n"
            + "                        \"a) commence\",\n"
            + "                        \"b) submit\",\n"
            + "                        \"c) approve\",\n"
            + "                        \"d) decline\"\n"
            + "                    ],\n"
            + "                    \"answer\": \"c\",\n"
            + "                    \"userAnswer\": \"a\",\n"
            + "                    \"correct\": false\n"
            + "                }\n"
            + "            ]\n"
            + "        },\n"
            + "        {\n"
            + "            \"solveHistoryId\": 25,\n"
            + "            \"solveHistoryDetail\": [\n"
            + "                {\n"
            + "                    \"question\": \"What does the word 'feasible' mean?\",\n"
            + "                    \"choices\": [\n"
            + "                        \"a) possible to do easily or conveniently\",\n"
            + "                        \"b) not clear or distinct\",\n"
            + "                        \"c) expensive and luxurious\",\n"
            + "                        \"d) dangerous and risky\"\n"
            + "                    ],\n"
            + "                    \"answer\": \"a\",\n"
            + "                    \"userAnswer\": \"a\",\n"
            + "                    \"correct\": true\n"
            + "                },\n"
            + "                {\n"
            + "                    \"question\": \"Which word is a synonym for 'enhance'?\",\n"
            + "                    \"choices\": [\n"
            + "                        \"a) diminish\",\n"
            + "                        \"b) improve\",\n"
            + "                        \"c) ignore\",\n"
            + "                        \"d) complicate\"\n"
            + "                    ],\n"
            + "                    \"answer\": \"b\",\n"
            + "                    \"userAnswer\": \"a\",\n"
            + "                    \"correct\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"question\": \"Choose the word that best fits the blank. 'She ___ her intention to resign from her position next month.'\",\n"
            + "                    \"choices\": [\n"
            + "                        \"a) developed\",\n"
            + "                        \"b) announced\",\n"
            + "                        \"c) questioned\",\n"
            + "                        \"d) explored\"\n"
            + "                    ],\n"
            + "                    \"answer\": \"b\",\n"
            + "                    \"userAnswer\": \"a\",\n"
            + "                    \"correct\": false\n"
            + "                }\n"
            + "            ]\n"
            + "        }")
    private List<SolveHistoryDetailResponse> solveHistoryDetail;

    @Builder
    public static SolveHistoryResponseDto of(Long solveHistoryId, ProblemType problemType,
            double correctRate, LocalDateTime solveDate, boolean isReviewed,
            List<SolveHistoryDetailResponse> solveHistoryDetail) {
        return new SolveHistoryResponseDto(solveHistoryId, problemType, correctRate, solveDate,
                isReviewed, solveHistoryDetail);
    }
}
