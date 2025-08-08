package com.shinhan.sbproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;


@Entity
@Table(name = "t_board")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BoardEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long bno;

    @NonNull //자바에서 필수 입력임을 의미
    @Column(nullable = false) // DB의 칼럼이 not null임을 의미
    String title;

    String writer;

    @Column(length = 200)
    String content;

    @CreationTimestamp // insert 시에 자동으로 현재 시간이 삽입됨
    Timestamp regDate;

    @UpdateTimestamp // insert, update 할 때, 값이 들어 온다.
    Timestamp updateDate;
}
