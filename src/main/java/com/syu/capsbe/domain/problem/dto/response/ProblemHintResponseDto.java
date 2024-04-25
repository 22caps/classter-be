package com.syu.capsbe.domain.problem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "문제 힌트 응답 정보")
public class ProblemHintResponseDto {

    @Schema(description = "힌트", example = "이 문장에서는 \"Due to unforeseen circumstances\" 즉, 예상치 못한 상황으로 인해 회의가 변경되었다고 말하고 있습니다. 선택지 중에서 이 상황에 맞는 단어를 고르면 됩니다. \n\na) canceled - 취소됨\nb) advanced - 앞당겨짐\nc) delayed - 지연됨\nd) extended - 연장됨\n\n이 경우, 예상치 못한 상황으로 회의가 취소되거나, 지연될 수 있습니다. 'extended'는 회의 시간이 늘어난다는 의미고, 'advanced'는 회의 시간이 앞당겨진다는 의미인데, 예상치 못한 상황으로 회의가 앞당겨지는 것은 자연스럽지 않습니다. 따라서 적절히 맞는 단어를 선택하시면 됩니다.")
    private final String hint;

    public static ProblemHintResponseDto of(String hint) {
        return new ProblemHintResponseDto(hint);
    }
}
