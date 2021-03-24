package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //User class가 자동으로 MySQL에 테이블이 생성된다.
public class User {

    @Id // Primary key 등록
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 데이터베이스의 넘버링 전략
    private int id; // auto-increment

    @Column(nullable = false, length = 30)
    private String username; // ID

    @Column(nullable = false, length = 100) // 해쉬값이 들어가서 길이가 길어야함
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role; // 추후 Enum으로 바꾸기*-*-0

    @CreationTimestamp // 시간이 자동으로  입력되도록 하는 어노테이션
    private Timestamp createDate;
}