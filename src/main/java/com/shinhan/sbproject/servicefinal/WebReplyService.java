package com.shinhan.sbproject.servicefinal;

import com.shinhan.sbproject.entityfinal.WebBoardDTO;
import com.shinhan.sbproject.entityfinal.WebBoardEntity;
import com.shinhan.sbproject.entityfinal.WebReplyDTO;
import com.shinhan.sbproject.paging.PageRequestDTO;
import com.shinhan.sbproject.paging.PageResultDTO;

import java.util.List;

public interface WebReplyService {

    //CRUD작업
    //1.조회
    List<WebReplyDTO> getList(Long bno);

    //2.상세보기
    WebReplyDTO selectById(Long rno);

    //3.입력
    int register(WebReplyDTO reply);

    //4.수정
    int modify(WebReplyDTO reply);

    //5.삭제
    int delete(Long rno);
}
