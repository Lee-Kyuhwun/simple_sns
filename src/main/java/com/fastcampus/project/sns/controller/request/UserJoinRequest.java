package com.fastcampus.project.sns.controller.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class UserJoinRequest {
    private String userName;
    private String password;
}
// 리퀘스트 바디로 데이터를 받아올텐데 그대사용할 패키지

