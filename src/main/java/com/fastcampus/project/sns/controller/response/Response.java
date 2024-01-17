package com.fastcampus.project.sns.controller.response;


// 획일화된 응답을 위한 클래스
public class Response<T> {
    private String resultCode;
    private T message;
    // T란 제네릭으로 어떤 타입이든 받을 수 있다.
    // 제네릭은 컴파일 시점에 타입이 결정된다.
    // 제네릭을 사용하려면 클래스 선언부에 <T>를 추가해야 한다.

}
