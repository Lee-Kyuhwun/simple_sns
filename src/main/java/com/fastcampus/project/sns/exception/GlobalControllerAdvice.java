package com.fastcampus.project.sns.exception;


import com.fastcampus.project.sns.controller.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice // 모든 컨트롤러에서 발생하는 예외를 잡아서 처리해준다.
public class GlobalControllerAdvice {


    // ResponseEntity는 HTTP상태코드를 반환해준다.
    // ?는 모든 타입을 의미한다.
    @ExceptionHandler(SnsApplicatoinException.class) // SnsApplicatoinException이 발생하면 이 메서드를 실행해준다.
    public ResponseEntity<?> handleSnsApplicationException(SnsApplicatoinException e) {
        log.error("errorCode : {}, message : {}", e.toString(), e.getMessage());
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(Response.error(e.getErrorCode().name())); // 에러코드를 반환해준다.
    }

    // ResponseEntity는 HTTP상태코드를 반환해준다.
    // ?는 모든 타입을 의미한다.
    @ExceptionHandler(RuntimeException.class) // SnsApplicatoinException이 발생하면 이 메서드를 실행해준다.
    public ResponseEntity<?> handleSnsApplicationException(RuntimeException e) {
        log.error("errorCode : {}, message : {}", e.toString(), e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response.error(ErrorCode.INTERNAL_SERVER_ERROR.name())); // 에러코드를 반환해준다.
    }

}
