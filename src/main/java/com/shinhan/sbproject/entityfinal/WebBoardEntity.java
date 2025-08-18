package com.shinhan.sbproject.entityfinal;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shinhan.sbproject.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@ToString(exclude = {"replies"})
@Table(name = "tbl_webboard")
public class WebBoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bno;
    String title;
    String writer;
    String content;


    //fetch는 LAZY가 default
    @JsonIgnore //JSON 생성시 무시된다.
//    @BatchSize(size = 100)
    @OneToMany(
            mappedBy = "board",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    List<WebReplyEntity> replies;

}
