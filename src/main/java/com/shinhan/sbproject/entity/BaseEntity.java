package com.shinhan.sbproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

//모든 entity들의 부모 entity로 사용될 예정이다.
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class}) //자식 Entity 변경 감시
@Getter
@ToString
public abstract class BaseEntity { //table은 생성되지 않음, 다른 entity의 부모

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name="moddate")
    private LocalDateTime modDate;


}
