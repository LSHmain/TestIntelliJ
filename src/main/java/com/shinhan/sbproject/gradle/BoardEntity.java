package com.shinhan.sbproject.gradle;

import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name="t_board")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto Increment
    Long bno;

    @NonNull // 자바에서 필수 입력임을 의미.
    @Column(nullable = false) // DB의 컬럼이 not null임을 의미
    String title;

    // 기본 컬럼 길이 : 255
    String writer;

    @Column(length = 500)
    String content;

    @CreationTimestamp // insert 시에 자동으로 현재 시간 입력
    Timestamp regDate;

    @UpdateTimestamp // insert, update 시에 자동으로 입력
    Timestamp updateDate;
}