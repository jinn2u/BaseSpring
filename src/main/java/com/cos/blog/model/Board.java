package com.cos.blog.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob  //대용량 데이터일 경우에 사용
    private  String Content; //섬머노트 라이브러리 사용(html태그가 섞여서 디자인이 됨.)

    private int count; //조회수

    @CreationTimestamp
    private Timestamp createDate;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    // 연관관계의 주인이 아니다. FK를 만들지 않는다.
    // 필요하면 reply를 들고오고 아니면 가져오지 않을수도 있다.(OneToMany의 기본 전력은 LAZY이다.)
    @JsonIgnoreProperties({"board"})
    // reply안의 board를 호출하지 않기 때문에 무한참조가 발생하지 않는다.
    @OrderBy("id desc")
    private List<Reply> replys;

    @ManyToOne(fetch = FetchType.EAGER) // board에는 하나의 user만 존재한다.(manyToOne의 기본전)
    @JoinColumn(name = "userId")
    private User user;
}
