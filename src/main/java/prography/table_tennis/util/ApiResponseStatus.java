package prography.table_tennis.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiResponseStatus {

    SUCCESS(200, "API 요청이 성공했습니다."),
    WRONG_REQUEST(201, "불가능한 요청입니다."),
    SERVER_ERROR(500, "에러가 발생했습니다.");

    private final int code;
    private final String message;

}
