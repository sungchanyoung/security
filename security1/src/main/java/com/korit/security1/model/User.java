package com.korit.security1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Entity
@Table(name = "users") // ✅ 테이블 이름 변경 (MySQL 예약어 충돌 방지)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String email;
    private String role;

    private String provider;// "로그인 하는방법"
    private String providerId;// "auttribute의 정보를 넣을거임" =>  회원가입을 강제로

    private  Timestamp loginDate;

    @Column(updatable = false) // 최초 생성 이후 업데이트 방지
    @CreationTimestamp
    private Timestamp createDate;


}
