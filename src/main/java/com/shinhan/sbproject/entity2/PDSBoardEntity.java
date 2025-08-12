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
@ToString
@Table(name = "t_pdsboard")
public class PDSBoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long pid;
    String pname;
    String pwriter;

    // fetch default 값은 LAZY
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pdsno") //t_pdsfile 테이블에 pdsno 칼럼이 생성
    List<PDSFileEntity> files2;
}
