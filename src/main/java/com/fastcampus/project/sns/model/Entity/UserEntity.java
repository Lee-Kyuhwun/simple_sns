package com.fastcampus.project.sns.model.Entity;


import com.fastcampus.project.sns.model.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * \"user\"라고 붙이는 이유는
 * postgresql에서 user라는 이름을 사용하고 있는데
 * user라는 이름을 사용하면 postgresql에서 에러가 발생하기 때문에
 * \"user\"라고 붙여준다.
 * \"user\"라고 붙이면 postgresql에서는 user라고 인식한다.
 * \"는 postgresql에서는 특수문자를 의미한다.
 *  */

@Entity
@Table(name = "\"user\"")
@Getter @Setter
@RequiredArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE \"user\" SET delete_at = now() WHERE id = ?")
// @SQLDelete는 엔티티를 삭제할 때 사용되는 SQL을 재정의하는 어노테이션이다.
@Where(clause = "delete_at is NULL")
// @Where는 엔티티를 조회할 때 사용되는 SQL을 재정의하는 어노테이션이다.
// delete_at이 null인 데이터만 조회하도록 설정
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // GenerationType.IDENTITY는 기본 키 생성을 데이터베이스에 위임하는 방법이다.
    private Integer id;

    @Column(name = "user_name")
    private String userName;


    @Column(name = "role")
    @Enumerated(EnumType.STRING) // EnumType.STRING은 enum의 이름을 데이터베이스에 저장한다.
    // 이게 있어야지 enum의 이름을 데이터베이스에 저장할 수 있다.
    private UserRole role=UserRole.USER;

    @Column(name = "password")
    private String password;

   @Column(name = "register_at")
    private Timestamp registerAt;
    // Timestamp는 날짜와 시간을 모두 저장하는 자바에서 제공하는 클래스


    @Column(name = "update_at")
    private Timestamp updateAt;

    @Column(name = "delete_at")
    private Timestamp deleteAt;

    // 데이터가 언제 저장 삭제 수정 되었는지 알아야지 수정이 쉬움

    @PrePersist // 데이터가 저장되기 전에 실행되는 메소드
    // @PrePersist는 엔티티가 저장되기 전에 자동으로 호출되는 메소드이다.
    void registeredAt() {
        this.registerAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    // @PreUpdate는 엔티티가 업데이트 되기 전에 자동으로 호출되는 메소드이다.
    void updatedAt() {
        this.updateAt = new Timestamp(System.currentTimeMillis());
    }

    // of는 생성자를 대신하는 메소드이다.
    // 생성자를 대신하는 이유는 생성자는 생성자의 파라미터가 많아지면 어떤 파라미터가 어떤 값을 의미하는지 알기 어렵기 때문이다.
    // 그래서 생성자를 대신하는 메소드를 만들어서 파라미터의 의미를 알기 쉽게 만든다.
    // 생성자를 대신하는 메소드는 static으로 선언한다.
    // static으로 선언하는 이유는 생성자를 대신하는 메소드는 객체를 생성하지 않고 사용하기 때문이다.
    public static UserEntity of(String userName, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.userName = userName;
        userEntity.password = password;
        return userEntity;
    }
    // of 메서드란 객체를 생성하는 메서드를 말한다.
    // of 메서드를 만드는 이유는 생성자를 대신하는 메서드를 만들기 위해서이다.
    /**
     * 유저 관련 서비스단에서는 dto를 사용하고
     * db 쪽에서만 이 유저 엔티티를 사용한다 왜냐하면
     * 이 두개가 혼용이 되면 클래스의 역할이 불분명해지기 때문이다.
     * 또한 Jpa를 사용중이라 클래스의 변화가 곧 db의 변화가 되기 때문에 민감하다.
     * 저희는 그냥 Dto를 변경하고 싶었고 필드 그냥 dto 자체에 있는 변경, DB 의 변경에는 영향을 주고 싶지않앗지만
     * 엔티티  클래스를 사용한다면 그런 실수를 할 가능성이 높다. 따라서 엔티티 클래스는 따로 분리하는 것이 좋다.
     * 그래서 서비스단에서는 Dto형태로 따로 만드는 것이 좋다.
     *
     * */
}
