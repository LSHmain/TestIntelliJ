package com.shinhan.sbproject.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WebReplyDTO {

    Long rno;
    String replyText;
    String replyer;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    Long bno;

}
