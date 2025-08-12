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
@Table(name = "t_usercellphone3")
public class UserCellPhoneEntity3 {

    @Id //칼럼 생성 안할것임
    String dummyId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    UserEntity3 user3;

    String phoneNumber;
    String model;


}
