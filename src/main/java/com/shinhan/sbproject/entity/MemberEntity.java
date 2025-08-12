package com.shinhan.sbproject.entity;


import jakarta.persistence.*;
import lombok.*;

//한명의 member가 profile을 여러개 가지고 있다.
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "t_member")
public class MemberEntity {
    @Id
    String mid;
    String mpassword;
    String mname;

    @Enumerated(EnumType.STRING)
    MemberRole mrole;

}
