package com.fastcampus.project.sns.controller.response;


// Dto 들은 서비스에서만 사용하는 것이 좋다.
// 왜냐하면 서비스에서 사용하는 것이 아니라 컨트롤러에서 사용하게 되면 컨트롤러에서 서비스에게 요청을 해야하는데 그러면 서비스가 컨트롤러에 의존하게 되는 것이다.
// 그래서 서비스에서 사용하는 것이 좋다.
// 따라서 컨트롤러에서만 사용할 Dto는 따로 만들어서 사용하는 것이 좋다.

import com.fastcampus.project.sns.model.User;
import com.fastcampus.project.sns.model.UserRole;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserJoinResponse {

    private String userName;

    private Integer id;

    private UserRole role;

    // Dto는 엔티티를 변환하는 역할을 한다.
    public static UserJoinResponse fromEntity(User user) {

        return new UserJoinResponse(user.getUserName(), user.getId(), user.getUserRole());
    }

}
