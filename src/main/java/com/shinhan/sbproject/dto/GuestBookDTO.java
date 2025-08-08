package com.shinhan.sbproject.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class GuestBookDTO {
    Long gno;
    String title;
    String writer;
    String content;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
