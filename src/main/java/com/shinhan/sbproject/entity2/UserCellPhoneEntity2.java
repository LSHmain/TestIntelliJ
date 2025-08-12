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
@Table(name = "t_usercellphone2")
public class UserCellPhoneEntity2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    Long phoneId;
    String phoneNumber;
    String model;

    //2. 대상 table에서 참조하기(비식별자)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    UserEntity2 user;

}
