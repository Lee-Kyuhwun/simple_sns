
package com.fastcampus.project.sns.controller;


import com.fastcampus.project.sns.controller.request.UserJoinRequest;
import com.fastcampus.project.sns.controller.request.UserLoginRequest;
import com.fastcampus.project.sns.controller.response.Response;
import com.fastcampus.project.sns.controller.response.UserJoinResponse;
import com.fastcampus.project.sns.controller.response.UserLoginResponse;
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
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest) {
        User user = userService.join(userJoinRequest.getUserName(), userJoinRequest.getPassword());
        return Response.success(UserJoinResponse.fromEntity(user));
    }


    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        String token = userService.login(userLoginRequest.getUserName(), userLoginRequest.getPassword());

        return Response.success(new UserLoginResponse(token));
    }
}
