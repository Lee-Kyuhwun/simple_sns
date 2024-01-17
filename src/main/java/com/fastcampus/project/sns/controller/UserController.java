
package com.fastcampus.project.sns.controller;


import com.fastcampus.project.sns.controller.request.UserJoinRequest;
import com.fastcampus.project.sns.controller.response.UserJoinResponse;
import com.fastcampus.project.sns.model.User;
import com.fastcampus.project.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/join")
    public void join(@RequestBody UserJoinRequest userJoinRequest) {
        User user = userService.join(userJoinRequest.getUserName(), userJoinRequest.getPassword());
        UserJoinResponse response = UserJoinResponse.fromEntity(user);
    }
}
