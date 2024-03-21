package com.fastcampus.project.sns.service;


import com.fastcampus.project.sns.exception.ErrorCode;
import com.fastcampus.project.sns.exception.SnsApplicatoinException;
import com.fastcampus.project.sns.model.Entity.UserEntity;
import com.fastcampus.project.sns.model.User;
import com.fastcampus.project.sns.repository.UserEntityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;


    private final BCryptPasswordEncoder encoder;



    @Transactional
    public User join(String userName, String password) {

        // 회원가입 하는 userName으로 이미 가입된 유저가 있는지 확인
        userEntityRepository.findByUserName(userName).ifPresent(it -> {
            throw new SnsApplicatoinException(ErrorCode.DUPLICATED_USERNAME,String.format("%s is duplicated username", userName));
        });



        // 없으면 회원가입 진행 회원가입 진행 = user 등록
        UserEntity userEntity = userEntityRepository.save(UserEntity.of(userName, encoder.encode(password))); // 비밀번호는 암호화해서 저장한다.



        return User.fromEntity(userEntity);
    }


    public String login(String username, String password) {
        // 회원가입 여부 체크
        UserEntity userEntity = userEntityRepository.findByUserName(username).orElseThrow(() -> new SnsApplicatoinException(ErrorCode.USER_NOT_FOUND, String.format("%s is not found", username)));

        // 비밀번호 체크

        // 암호화된거라서 비교를 다르게 해야함
        if(!encoder.matches(password, userEntity.getPassword())) { // matches는 암호화된 비밀번호와 비교하는 메소드
            throw new SnsApplicatoinException(ErrorCode.INVALID_PASSWORD, String.format("%s is not found", username));
        }


        // 토큰 생성

        return "";
    }
}
