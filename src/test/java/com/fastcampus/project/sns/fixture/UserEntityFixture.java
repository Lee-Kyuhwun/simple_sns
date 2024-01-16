package com.fastcampus.project.sns.fixture;

import com.fastcampus.project.sns.model.Entity.UserEntity;

public class UserEntityFixture {

    // Fixture: 테스트를 위한 데이터를 미리 만들어두는 것


    public static UserEntity get(String userName, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setUserName(userName);
        userEntity.setPassword(password);
        return userEntity;
    }



}
