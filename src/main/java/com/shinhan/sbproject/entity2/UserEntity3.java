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
@Table(name = "t_user3")
public class UserEntity3 {

    @Id
    @Column(name = "user_id") // 부모와 동일한 이름, 타입
    String userId;

    @Column(name = "user_name")
    String username;


    //3. 주 table에서 참조
    //mappedBy는 실제 칼럼 생성 안됨, 메어있음
    @OneToOne(
            cascade = CascadeType.ALL
            , mappedBy = "user3"
    )
    UserCellPhoneEntity3 phone;
}
