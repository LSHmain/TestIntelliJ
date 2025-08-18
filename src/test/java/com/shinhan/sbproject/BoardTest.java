package com.shinhan.sbproject;

import com.shinhan.sbproject.entity.BoardEntity;
import com.shinhan.sbproject.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardTest {
    @Autowired
    BoardRepository boardRepository;

    @Test
    void f_insertt(){
        IntStream.rangeClosed(1,20).forEach(i->{
            BoardEntity board = BoardEntity
                    .builder()
                    .title("게시글"+i)
                    .content("오늘은 목요일"+i)
                    .writer("작성자"+i%5)
                    .build();
            boardRepository.save(board); // insert into ~~~
        });
    }

    @Test
    void f_select() {
        boardRepository.findAll().forEach((board -> {
            System.out.println(board);
        }));
    }

    @Test
    void f_selectById() {
        List<Long> boardList = Arrays.asList(10L, 20L, 30L);
        for (Long boardId : boardList) {
            boardRepository.findById(boardId).ifPresentOrElse(board -> {
                System.out.println(board);
            },() -> {
                System.out.println("존재하지 않습니다.");
            });
        }
//
//        Long bno = 4L;
//        boardRepository.findById(bno).ifPresentOrElse(board -> {
//            System.out.println(board);
//        }, () -> {
//            System.out.println("존재하지 않습니다.");
//        });
    }

    @Test
    void f_update(){
        Long bno = 3L;
        boardRepository.findById(bno).ifPresentOrElse(board -> {
            System.out.println("수정");
            board.setWriter("wow");
            boardRepository.save(board);
        }, () -> {
            System.out.println("존재하지 않습니다.");
        });
    }


    @Test
    void f_delete() {
        Long bno = 4L;
        boardRepository.deleteById(bno);
    }

    @Test
    void f_count(){
        System.out.println("전체 건수:" +boardRepository.count());
    }

    @Test
    void f_deleteAll(){
        boardRepository.deleteAll();
    }

    @Test
    void f_findAll(){
        boardRepository.findAll().forEach(board -> {
            System.out.println(board.getWriter());
        });
    }

    //기본 CRUD 기능이 없는 경우...규칙에 맞는 함수를 정의하여 사용한다.
    @Test
    void f_writer(){
        boardRepository.findAllByWriter("작성자1").forEach(board -> {
            System.out.println(board);
        });
    }


    @Test
    void f_title(){
        boardRepository.findAllByTitleContaining("1").forEach(board -> {
            System.out.println(board);
        });
    }

    @Test
    void f_title_start(){
        boardRepository.findAllByTitleStartsWith("게시글1").forEach(board -> {
            System.out.println(board);
        });
    }

//    @Test
//    void f_paging() {
//        Long bno = 10L;
//        int page=0, size=5;
//        Pageable paging = PageRequest.of(page, size);
////      boardRepo.findByBnoGreaterThanOrderByBnoDesc(bno, paging).forEach(board -> System.out.println(board));
//        boardRepository.findAll(paging).forEach(board->System.out.println(board));
//
//        Page<BoardEntity> result = boardRepository.findByBnoGreaterThanOrderByBnoAsc(bno, paging);
////        System.out.println("현재 page: " + result.getNumber());
////        System.out.println("현재 page 건수: " + result.getSize());
////        System.out.println("page의 개수: " + result.getTotalPages());
////        System.out.println("전체 page 건수: " + result.getTotalElements());
////        System.out.println("다음 page: " + result.nextPageable());
//        System.out.println(result);
//    }



}
