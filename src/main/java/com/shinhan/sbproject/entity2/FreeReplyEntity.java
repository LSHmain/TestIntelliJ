package com.shinhan.sbproject.entity2;


import com.shinhan.sbproject.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@ToString
@Table(name = "t_freereply")
public class FreeReplyEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long rno;
    String reply;
    String replyer;

    @ManyToOne
    FreeBoardEntity board; //칼럼이름은 board_bno

}
