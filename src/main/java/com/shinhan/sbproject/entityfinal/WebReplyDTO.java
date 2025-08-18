package com.shinhan.sbproject.entityfinal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;


//유저가 요청한건 DTO
//DB를 가져올떈 Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WebReplyDTO {

    Long rno;
    String replyText;
    String replyer;
    LocalDateTime regDate;
    private LocalDateTime modDate;
    Long bno;
}
