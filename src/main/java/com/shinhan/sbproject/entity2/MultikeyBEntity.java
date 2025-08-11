package com.shinhan.sbproject.entity2;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "t_childB")
public class MultikeyBEntity {
    @EmbeddedId
    MultiKeyB id;

    String name;
}
