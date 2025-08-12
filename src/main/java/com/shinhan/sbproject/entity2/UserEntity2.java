package com.shinhan.sbproject.entity2;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "t_user2")
public class UserEntity2 {

    @Id
    @Column(name = "user_id")
    String userid;

    @Column(name = "user_name")
    String username;

    //1. 주 테이블에서 참조하기
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_id")//주테이블에 칼럼 생성
    UserCellPhoneEntity phone;
}
