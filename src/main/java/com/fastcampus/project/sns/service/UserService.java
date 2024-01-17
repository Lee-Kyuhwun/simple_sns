package com.fastcampus.project.sns.service;


import com.fastcampus.project.sns.exception.SnsApplicatoinException;
import com.fastcampus.project.sns.model.Entity.UserEntity;
import com.fastcampus.project.sns.model.User;
import com.fastcampus.project.sns.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;






    // TODO: 회원가입 서비스구현
    public User join(String userName, String password) {

        // 회원가입 하는 userName으로 이미 가입된 유저가 있는지 확인
        userEntityRepository.findByUserName(userName).ifPresent(it -> {
            throw new SnsApplicatoinException();
        });



        // 없으면 회원가입 진행
        UserEntity userEntity = userEntityRepository.save(UserEntity.of(userName, password));



        return User.fromEntity(userEntity);
    }


    public String login(String username, String password) {
        // 회원가입 여부 체크
        UserEntity userEntity = userEntityRepository.findByUserName(username).orElseThrow(() -> new SnsApplicatoinException());

        // 비밀번호 체크
        if(!userEntity.getPassword().equals(password)) {
            throw new SnsApplicatoinException();
        }


        // 토큰 생성

        return "";
    }
}
