package com.shinhan.sbproject.entityfinal;

import com.shinhan.sbproject.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

// 1명의 member가 여러 profile을 가짐
// profile은 여러개, 여러개의 profile이 한 member의 것
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString//(exclude = {"member"}) // ToString시 member정보를 안들고오게 제외
@Builder
@Entity
@Table(name = "t_profile")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long fno;
    String fname;
    boolean currentYn;

    // jpa가 아닌 경우 String mid;...처럼 사용
    // 연관 관계 추가함
    // FetchType.LAZY 지연 로딩 => toString()에 반드시 exclude 추가
    // FetchType.EAGER 즉시 로딩
//    @ManyToOne(fetch = FetchType.LAZY) // 여러개에서 하나를 참조
    @ManyToOne(fetch = FetchType.EAGER) // 여러개에서 하나를 참조
            MemberEntity member; // db의 컬럼 이름은 member_mid
}
