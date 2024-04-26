package com.syu.capsbe.domain.solveHistory.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "풀이 기록 요청 정보")
@AllArgsConstructor
public class SolveHistoryRequestDto {

    private String problemType;

    private List<SolveHistoryDetailRequestDto> solveHistoryDetail;
}
