package com.shinhan.sbproject.entity;


import jakarta.persistence.*;
import lombok.*;

//프로파일은 여러개, 여러개의 profile이 한 멤버의 것이다.
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long fno;
    String fname;
    boolean currentYn;

    //JPA가 아닌 경우 String mid;
    //***연관관계 추가함
    //@ManyToOne fetch 의 Default는 EAGER(즉시로딩)
    //LAZY는 지연로딩 필요할때만 가져옴
    @ManyToOne(fetch = FetchType.EAGER)
    MemberEntity member; //DB에 칼럼이름은 member_mid
}
