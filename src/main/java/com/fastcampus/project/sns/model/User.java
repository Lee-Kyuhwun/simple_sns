package com.fastcampus.project.sns.model;


import com.fastcampus.project.sns.model.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter@Setter
public class User {
    private Integer id;
    private String userName;
    private String password;

    private UserRole userRole;

    private Timestamp registerAt;
    private Timestamp updateAt;
    private Timestamp deleteAt;

    // Entity를 Dto로 변환해주는 메서드
    public static User fromEntity(UserEntity entity) {
        return new User(entity.getId(), entity.getUserName(), entity.getPassword(), entity.getRole(), entity.getRegisterAt(), entity.getUpdateAt(), entity.getDeleteAt());
    }

}
