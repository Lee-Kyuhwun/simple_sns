package com.fastcampus.project.sns.controller.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// 획일화된 응답을 위한 클래스
public class Response<T> {
    private String resultCode;
    private T message;
    // T란 제네릭으로 어떤 타입이든 받을 수 있다.
    // 제네릭은 컴파일 시점에 타입이 결정된다.
    // 제네릭을 사용하려면 클래스 선언부에 <T>를 추가해야 한다.





    // 에러 응답을 위한 메소드
    public static Response<Void> error(String errorCode){ // 제네릭 메소드
        return new Response<>(errorCode, null);
    }
    // Void는 java lang 패키지에 있는 클래스로서 null을 의미한다.
    // 에러인 경우 result가 없으므로 보이드로 반환해준다.

    // 성공 응답을 위한 메서드
    public static <T> Response<T> success(T message){
        return new Response<>("SUCCESS", message);
    }



}
