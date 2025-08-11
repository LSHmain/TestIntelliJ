package com.shinhan.sbproject.entity2;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "t_childA")
@IdClass(MultiKeyA.class)
public class MultikeyAEntity {

    @Id
    Integer id1;
    @Id
    Integer id2;
    String name;
}
