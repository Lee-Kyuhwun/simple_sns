package com.fastcampus.project.sns.exception;


//TODO: implement

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class SnsApplicatoinException extends RuntimeException{

    // 에러코드를 Enum으로 정의해주고 그걸 사용한다.
    private ErrorCode errorCode;

    private String message;

    public SnsApplicatoinException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = null;
    }

    @Override
    public String getMessage() {
        if(message == null){
            return errorCode.getMessage();
        }
        return String.format("%s : %s", errorCode.getMessage(), message); // 에러코드와 메시지를 합쳐서 반환해준다.
    }
}
