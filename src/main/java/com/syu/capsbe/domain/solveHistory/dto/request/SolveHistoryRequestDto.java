package com.syu.capsbe.domain.solveHistory.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "풀이 기록 요청 정보")
@AllArgsConstructor
public class SolveHistoryRequestDto {

    @Schema(description = "문제 유형", example = "GRAMMAR | CONVERSATION | WORD")
    private String problemType;

    @Schema(description = "풀이 세부 기록 리스트", example = "[\n"
            + "                {\n"
            + "                    \"question\": \"What is the best synonym for 'enduring' in the context of business relationships? Choices: a) lasting, b) brief, c) fragile, d) sporadic\",\n"
            + "                    \"answer\": \"a\",\n"
            + "                    \"userAnswer\": \"b\",\n"
            + "                    \"correct\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"question\": \"Identify the error in the sentence: 'She has less clients than she did last year.' Choices: a) She has, b) less, c) than she, d) last year\",\n"
            + "                    \"answer\": \"b\",\n"
            + "                    \"userAnswer\": \"b\",\n"
            + "                    \"correct\": true\n"
            + "                },\n"
            + "                {\n"
            + "                    \"question\": \"What does the phrase 'touch base' mean in a business context? Choices: a) To negotiate, b) To update, c) To argue, d) To finalize\",\n"
            + "                    \"answer\": \"b\",\n"
            + "                    \"userAnswer\": \"b\",\n"
            + "                    \"correct\": true\n"
            + "                }\n"
            + "            ]\n"
            + "        },\n"
            + "        {\n"
            + "            \"solveHistoryCount\": 2,\n"
            + "            \"solveHistoryDetail\": [\n"
            + "                {\n"
            + "                    \"question\": \"What is the best synonym for 'enduring' in the context of business relationships? Choices: a) lasting, b) brief, c) fragile, d) sporadic\",\n"
            + "                    \"answer\": \"a\",\n"
            + "                    \"userAnswer\": \"a\",\n"
            + "                    \"correct\": true\n"
            + "                },\n"
            + "                {\n"
            + "                    \"question\": \"Identify the error in the sentence: 'She has less clients than she did last year.' Choices: a) She has, b) less, c) than she, d) last year\",\n"
            + "                    \"answer\": \"b\",\n"
            + "                    \"userAnswer\": \"b\",\n"
            + "                    \"correct\": true\n"
            + "                },\n"
            + "                {\n"
            + "                    \"question\": \"What does the phrase 'touch base' mean in a business context? Choices: a) To negotiate, b) To update, c) To argue, d) To finalize\",\n"
            + "                    \"answer\": \"b\",\n"
            + "                    \"userAnswer\": \"b\",\n"
            + "                    \"correct\": true\n"
            + "                }\n"
            + "            ]")
    private List<SolveHistoryDetailRequestDto> solveHistoryDetail;
}
