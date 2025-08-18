package com.shinhan.sbproject.dto;

import com.shinhan.sbproject.entityfinal.WebReplyEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WebBoardDTO {
    private Long bno;
    private String title;
    private String writer;
    private String content;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    List<WebReplyEntity> webReplyEntities;
    Long replyCount;
}
