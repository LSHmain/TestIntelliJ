package com.shinhan.sbproject.entity2;

import com.shinhan.sbproject.entity.MemberRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_enum")
public class EnumTypeEntity {
    @Id
    private String mid;
    private String mpassword;
    private String mname;
    //@CollectionTable이 생략되면 테이블이름은 enum_type_entity_mrole
    @ElementCollection(targetClass = MemberRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "tbl_enum_mroles",
            joinColumns = @JoinColumn(name = "mid"))// tbl_enum_mroles테이블의 조인칼럼
    private Set<MemberRole> mrole;
}