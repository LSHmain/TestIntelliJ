package com.shinhan.sbproject.entity2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shinhan.sbproject.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@ToString(exclude = "replies")
@Table(name = "t_freeboard")
public class FreeBoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String writer;
    String content;

    @JsonIgnore //Json으로 return시 replies 제외
    //@ToString(exclude = "replies")와 비슷한 개념
    @OneToMany(
            cascade = CascadeType.ALL
            , fetch = FetchType.LAZY
            , mappedBy = "board"
    )
    List<FreeReplyEntity> replies;
}
