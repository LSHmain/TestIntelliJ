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
@Table(name = "t_pdsfile")
public class PDSFileEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long fno;
    String pdsfilename;

}
