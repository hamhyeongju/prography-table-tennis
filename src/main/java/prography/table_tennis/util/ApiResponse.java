package prography.table_tennis.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import static prography.table_tennis.util.ApiResponseStatus.SUCCESS;

@Getter
public class ApiResponse<T> {
    private Integer code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public ApiResponse() {
        success();
    }

    public ApiResponse(T result) {
        success();
        this.result = result;
    }

    private void success() {
        this.code = SUCCESS.getCode();
        this.message = SUCCESS.getMessage();
    }
}
