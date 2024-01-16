package com.fastcampus.project.sns.controller;


import com.fastcampus.project.sns.controller.request.UserJoinRequest;
import com.fastcampus.project.sns.exception.SnsApplicatoinException;
import com.fastcampus.project.sns.model.User;
import com.fastcampus.project.sns.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMVC란, 서버를 띄우지 않고도 스프링 MVC 동작을 재현할 수 있는 클래스이다.
    // MockMVC는 서블릿 컨테이너를 구동하지 않기 때문에, 테스트가 빠르고 가볍다는 장점이 있다.

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper란, JSON 데이터를 자바 객체로 변환해주는 라이브러리이다.

    @MockBean // MockBean이란, 스프링이 관리하는 빈을 Mock으로 대체하는 어노테이션이다.
    // MockBean을 사용하면, 실제 빈을 사용하는 것처럼 테스트를 진행할 수 있다.
    private UserService userService;


    @Test
    @DisplayName("회원가입")
   public void signup() throws Exception {
        String userName = "username";
        String password = "password";

        //TODO: MOcking
        when(userService.join(userName,password)).thenReturn(mock(User.class));

        mockMvc.perform(post("/api/v1/users/join")
                .contentType(MediaType.APPLICATION_JSON) // 컨텐트타입이란 주석: 요청의 본문에 어떤 형식의 데이터가 담겨있는지 알려주는 역할을 한다.
                // TODO: 리퀘스트 바디 채우기
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))) // TODO: objectMapper.writeValueAsBytes()가 무엇인지 알아보기
                .andDo(print()) // TODO: print()가 무엇인지 알아보기
                // andDo(print())를 사용하면 요청과 응답을 콘솔에 출력할 수 있다.
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("회원가입시 이미 회원가입된 유저네임으로 회원가입을 할 수 없다.")
    public void signUpFail() throws Exception {
        String userName = "username";
        String password = "password";

        //mocking이란 실제 객체를 만들지 않고 가짜 객체를 만들어서 테스트를 진행하는 것이다.

        //TODO: implement
        when(userService.join()).thenThrow(new SnsApplicatoinException());

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        // TODO: 리퀘스트 바디 채우기
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))) // TODO: objectMapper.writeValueAsBytes()가 무엇인지 알아보기
                .andDo(print())
                .andExpect(status().isConflict());
    }


    @Test
    @DisplayName("로그인시 회원가입이 안된 유저 네임으로 로그인을 할 수 없다.")
    public void login() throws Exception {
        String userName = "username";
        String password = "password";

        //TODO: MOcking
        when(userService.login()).thenThrow(new SnsApplicatoinException());

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON) // 컨텐트타입이란 주석: 요청의 본문에 어떤 형식의 데이터가 담겨있는지 알려주는 역할을 한다.
                        // TODO: 리퀘스트 바디 채우기
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))) // TODO: objectMapper.writeValueAsBytes()가 무엇인지 알아보기
                .andDo(print()) // TODO: print()가 무엇인지 알아보기
                // andDo(print())를 사용하면 요청과 응답을 콘솔에 출력할 수 있다.
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인시 회원가입이 안된 유저 네임으로 로그인을 할 수 없다.")
    public void login_fail() throws Exception {
        String userName = "username";
        String password = "password";

        //TODO: MOcking
        when(userService.login()).thenThrow(new SnsApplicatoinException());

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON) // 컨텐트타입이란 주석: 요청의 본문에 어떤 형식의 데이터가 담겨있는지 알려주는 역할을 한다.
                        // TODO: 리퀘스트 바디 채우기
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))) // TODO: objectMapper.writeValueAsBytes()가 무엇인지 알아보기
                .andDo(print()) // TODO: print()가 무엇인지 알아보기
                // andDo(print())를 사용하면 요청과 응답을 콘솔에 출력할 수 있다.
                .andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("로그인시_틀린_password_입력시_에러가_발생한다.")
    public void login_wrong_password() throws Exception {
        String userName = "username";
        String password = "password";

        //TODO: MOcking
        when(userService.login()).thenThrow(new SnsApplicatoinException());

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON) // 컨텐트타입이란 주석: 요청의 본문에 어떤 형식의 데이터가 담겨있는지 알려주는 역할을 한다.
                        // TODO: 리퀘스트 바디 채우기
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))) // TODO: objectMapper.writeValueAsBytes()가 무엇인지 알아보기
                .andDo(print()) // TODO: print()가 무엇인지 알아보기
                // andDo(print())를 사용하면 요청과 응답을 콘솔에 출력할 수 있다.
                .andExpect(status().isUnauthorized());
    }

}
