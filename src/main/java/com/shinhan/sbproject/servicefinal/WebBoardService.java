package com.shinhan.sbproject.servicefinal;

import com.shinhan.sbproject.entityfinal.WebBoardDTO;
import com.shinhan.sbproject.entityfinal.WebBoardEntity;
import com.shinhan.sbproject.paging.PageRequestDTO;
import com.shinhan.sbproject.paging.PageResultDTO;

import java.util.List;

public interface WebBoardService {

    //CRUD작업
    //1.조회
    List<WebBoardDTO> getList();




    // paging, 조건조회
    public PageResultDTO<WebBoardDTO, WebBoardEntity> getList(PageRequestDTO pageRequestDTO);

    //2.상세보기
    WebBoardDTO selectById(Long bno);

    //3.입력
    int register(WebBoardDTO board);

    //4.수정
    int modify(WebBoardDTO board);

    //5.삭제
    int delete(Long bno);

    List<WebBoardDTO> selectAll();
}
