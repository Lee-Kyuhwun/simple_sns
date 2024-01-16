package com.fastcampus.project.sns.service;

import com.fastcampus.project.sns.exception.SnsApplicatoinException;
import com.fastcampus.project.sns.fixture.UserEntityFixture;
import com.fastcampus.project.sns.model.Entity.UserEntity;
import com.fastcampus.project.sns.repository.UserEntityRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class UserServiceTest {


    @Autowired
    private UserService userService;


    @MockBean // MockBean이란, 스프링이 관리하는 빈을 Mock으로 대체하는 어노테이션이다.
    // Mock이란 실제 객체와 비슷하게 동작하지만, 프로그래머가 직접 그 객체의 행동을 관리할 수 있는 가짜 객체이다.
    // MockBean을 사용하면, 실제 빈을 사용하는 것처럼 테스트를 진행할 수 있다.
    private UserEntityRepository userEntityRepository;

    @Test
    void 회원가입_정상적으로_동작하는지_확인() {
        //given
        String userName = "username";
        String password = "password";

        //mocking
        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.empty());// 회원가입을 한적이 없기때문에 Optional.empty()를 반환한다.
        when(userEntityRepository.save(any())).thenReturn(Optional.of(UserEntityFixture.get(userName, password)));// 회원가입을 하면 Optional.of(mock(UserEntity.class))를 반환한다.
        // any()는 어떤 인자가 들어와도 상관없다는 의미이다.
        // Opriotnal.of()는 Optional 객체를 생성하는 메소드이다.

        //when
        Assertions.assertDoesNotThrow(() -> userService.join(userName, password)); // 회원가입을 하면 SnsApplicatoinException.class를 반환한다.
        // assertThrows()는 예외가 발생하는지 확인하는 메소드이다.


        //then
    }


    @Test
    void 회원가입_userName이_이미있는경우() {
        //given
        String userName = "username";
        String password = "password";
        UserEntity fixture = UserEntityFixture.get(userName, password);

        //mocking
        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(fixture));// 회원가입을 한적이 없기때문에 Optional.empty()를 반환한다.
        when(userEntityRepository.save(any())).thenReturn(Optional.of(fixture));// 회원가입을 하면 Optional.of(mock(UserEntity.class))를 반환한다.
        // any()는 어떤 인자가 들어와도 상관없다는 의미이다.
        // Opriotnal.of()는 Optional 객체를 생성하는 메소드이다.

        //when
        Assertions.assertThrows(SnsApplicatoinException.class, () -> userService.join(userName, password)); // 회원가입을 하면 SnsApplicatoinException.class를 반환한다.
        // assertThrows()는 예외가 발생하는지 확인하는 메소드이다.


        //then
    }



    @Test
    void 로그인이_정상적으로_동작하는지_확인() {
        //given
        String userName = "username";
        String password = "password";

        UserEntity fixture = UserEntityFixture.get(userName, password);

        //when
        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(fixture));// 회원가입을 하면 Optional.of(mock(UserEntity.class))를 반환한다.
        when(userEntityRepository.save(any())).thenReturn(Optional.of(mock(UserEntity.class)));// 회원가입을 하면 Optional.of(mock(UserEntity.class))를 반환한다.

        Assertions.assertThrows(SnsApplicatoinException.class, () -> userService.login(userName, password)); // 회원가입을 하면 SnsApplicatoinException.class를 반환한다.
        // assertThrows()는 예외가 발생하는지 확인하는 메소드이다.


        //then
    }

    @Test
    void 로그인시_userName으로_회원가입한_유저가_있는경우() {
        //given
        String userName = "username";
        String password = "password";
        String wrongPassword = "wrongPassword";

        UserEntity fixture = UserEntityFixture.get(userName, password);

        //when
        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(fixture));// 회원가입을 하면 Optional.of(mock(UserEntity.class))를 반환한다.

        Assertions.assertThrows(SnsApplicatoinException.class, () -> userService.login(userName, wrongPassword)); // 회원가입을 하면 SnsApplicatoinException.class를 반환한다.
        // assertThrows()는 예외가 발생하는지 확인하는 메소드이다.


        //then
    }


    @Test
    void 로그인시_password가틀린경우() {
        //given
        String userName = "username";
        String password = "password";


        //when
        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.empty());// 회원가입을 하면 Optional.of(mock(UserEntity.class))를 반환한다.

        Assertions.assertThrows(SnsApplicatoinException.class, () -> userService.login(userName, password)); // 회원가입을 하면 SnsApplicatoinException.class를 반환한다.
        // assertThrows()는 예외가 발생하는지 확인하는 메소드이다.


        //then
    }

}