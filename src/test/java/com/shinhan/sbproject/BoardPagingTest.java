package com.shinhan.sbproject;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shinhan.sbproject.entity.BoardEntity;
import com.shinhan.sbproject.entity.QBoardEntity;
import com.shinhan.sbproject.repository.BoardPagingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
class BoardPagingTest {
    @Autowired
    BoardPagingRepository boardRepo;

    @Test
    void findByBnoGreaterThanOrderByBnoDesc() {
        Long bno = 11L;
        int page = 0, size = 5; //page는 0부터 있음, 1은 두번째 페이지임
        Pageable paging = PageRequest.of(page, size, Sort.Direction.DESC, "writer");
        Page<BoardEntity> result = boardRepo.findByBnoGreaterThanOrderByBnoAsc(bno, paging);
        System.out.println("현재 page : " + result.getNumber());
        System.out.println("현재 page 건수 : " + result.getSize());
        System.out.println("page 건수 : " + result.getTotalPages());
        System.out.println("전체 page 건수 : " + result.getTotalElements());
        System.out.println("다음 page 정보 : " + result.nextPageable());
        List<BoardEntity> boardList = result.getContent();
        System.out.println("내용 : " + boardList);
    }


    @Test
    void f_jpqlTest(){
        String writer = "작성자1";
        String content = "목요일";
        System.out.println("-----------------");
        boardRepo.findAllByWriter3(writer,content).forEach(System.out::println);
    }

    // -----QueryDSL 이용하기
    @Test
    void f_queryDSLTest() {
        String writer = "작성자1";
        String content = "목요일";
        Timestamp dt = Timestamp.valueOf("2025-08-07 00:00:00");

        BooleanBuilder predicate = new BooleanBuilder();
        QBoardEntity board = QBoardEntity.boardEntity;

        predicate.and(board.writer.eq(writer));
        predicate.and(board.content.like("%"+content+"%"));

        predicate.and(board.bno.gt(25L));
        predicate.and(board.regDate.gt(dt));

        System.out.println(predicate);
        boardRepo.findAll(predicate).forEach(System.out::println);
    }

    @Autowired
    JPAQueryFactory  jpaQueryFactory;



    //1. 기본 CRUD 함수
    //2. 규칙에 맞는 함수 정의 findBy~~~ 제한적, 복잡한 SQL 불가
    //3. JPQL...@Query, 실수, 한계, nativeQuery 비권장
    //4. QueryDSL 이용...동적 SQL문 만들기
    @Test
    void f_jpa(){
        QBoardEntity board = QBoardEntity.boardEntity;

        List<BoardEntity> boardList = jpaQueryFactory.selectFrom(board)
                .where(board.writer.eq("작성자1")
                .and(board.bno.gt(25L))
                ).fetch();

        boardList.forEach(boardL -> System.out.println(boardL));

    }


}
