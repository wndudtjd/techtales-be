package techtales.techtalesbe.common.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import techtales.techtalesbe.common.exception.CustomErrorCode;

@Builder
public record ResponseMessage<T>(String message, int statusCode, T data) {
    public static ResponseEntity<ResponseMessage<Object>> ErrorResponse(CustomErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ResponseMessage.<Object>builder()
                        .statusCode(errorCode.getHttpStatus().value())
                        .message(errorCode.getMessage())
                        .data("")
                        .build()
                );
    }

    public static <T> ResponseEntity<ResponseMessage<T>> SuccessResponse(String message, T data) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseMessage.<T>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(message)
                        .data(data)
                        .build()
                );
    }

    //valid 오류처리
    public static <T> ResponseEntity<ResponseMessage<T>>ValidResponse(String message){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseMessage.<T>builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message(message)
                        .build()
                );

    }
}
