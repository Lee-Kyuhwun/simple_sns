
package com.fastcampus.project.sns.controller;


import com.fastcampus.project.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    // TODO: 회원가입 구현
    @PostMapping("/join")
    public void join(String userName, String password) {
        userService.join(String userName, String password);
    }
}
