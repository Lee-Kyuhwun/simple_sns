package com.fastcampus.project.sns.repository;

import com.fastcampus.project.sns.model.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long>{


    // 값이 없을 수도 있으니 그걸 Optional로 감싸서 반환
    // Optional은 값이 있을 수도 있고 없을 수도 있음을 표현하는 자바 8에 추가된 컨테이너 클래스
    Optional<UserEntity> findByUserName(String userName);
}
