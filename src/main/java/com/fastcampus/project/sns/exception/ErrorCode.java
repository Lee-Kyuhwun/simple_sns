package com.fastcampus.project.sns.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "duplicate username"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "password wrong"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server error")

    ;

    private HttpStatus status;
    // 에러코드를 정의할 때는 에러코드와 에러메시지, 그리고 에러의 상태코드를 정의해준다.
    // HTTPSTATUS를 사용하는 이유는 에러코드를 정의할 때 HTTP상태코드를 정의해주는 것이 좋기 때문이다.
    // 예를 들어서 404는 NOT_FOUND이고 500은 INTERNAL_SERVER_ERROR이다.
    // 그래서 HTTP상태코드를 정의해주는 것이 좋다.

    private String message;





}
