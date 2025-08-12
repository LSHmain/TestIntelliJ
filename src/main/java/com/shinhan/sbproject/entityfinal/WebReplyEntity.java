package com.shinhan.sbproject.entityfinal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shinhan.sbproject.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@ToString(exclude = {"board"})
@Table(name = "tbl_webreply")
public class WebReplyEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long rno;
    String replyText;
    String replyer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    WebBoardEntity board; //column name: 앞의 이름과 반대쪽 참조하는 ID 합쳐져 board_bno가 생성된다.
}
