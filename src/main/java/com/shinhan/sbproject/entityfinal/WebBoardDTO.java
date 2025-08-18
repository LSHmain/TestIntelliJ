package com.shinhan.sbproject.entityfinal;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@ToString
public class WebBoardDTO {

    Long bno;
    String title;
    String writer;
    String content;
    private String imageUrl;
    LocalDateTime regDate, modDate;
    List<WebReplyEntity> replies;
    Long replyCount;

}
