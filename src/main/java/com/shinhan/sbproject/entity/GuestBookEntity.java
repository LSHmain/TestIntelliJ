package com.shinhan.sbproject.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="t_guestbook")
@Getter
@Setter
@Builder
public class GuestBookEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long gno;
    String title;
    String writer;
    String content;

    @Override
    public String toString() {
        return "GuestBookEntity{" +
                "gno=" + gno +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
