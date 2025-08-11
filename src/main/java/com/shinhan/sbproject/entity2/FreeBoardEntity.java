package com.shinhan.sbproject.entity2;

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

    @OneToMany(
            cascade = CascadeType.ALL
            , fetch = FetchType.LAZY
            , mappedBy = "board"
    )
    List<FreeReplyEntity> replies;
}
