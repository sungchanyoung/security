package com.korit.security1.repository;

import com.korit.security1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//CRUD 함수를 JPARepository가 들고 있음
// @Repository라는 어노테이션이 없어도 IoC되요이유는 JPARepository를 상속 했기 떄문에
public interface UserRepository extends JpaRepository<User, Integer> {

    //select * from user where username = 1?
    public User findByUsername(String username);//Jpa 쿼리 메서드

}
