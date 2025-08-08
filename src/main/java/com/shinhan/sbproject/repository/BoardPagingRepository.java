package com.shinhan.sbproject.repository;

import com.shinhan.sbproject.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardPagingRepository extends PagingAndSortingRepository<BoardEntity, Long>
        , CrudRepository<BoardEntity, Long>
        , QuerydslPredicateExecutor<BoardEntity>{

    Page<BoardEntity> findByBnoGreaterThanOrderByBnoAsc(Long bno, Pageable paging);

    //3. JPQL(JPA Query Language)...SQL문이 복잡한 경우, 함수 이름 규칙 없음
    @Query("""
                select board
                from BoardEntity board
                where board.writer like :ww
                and board.content like concat('%', :cc, '%')
            """)
    List<BoardEntity> findAllByWriter2(@Param("ww") String writer, @Param("cc") String content);


    @Query("""
                select board
                from  #{#entityName} board
                where board.writer like :ww
                        and board.content like %:cc%
            """)
    List<BoardEntity> findAllByWriter3(@Param("ww") String writer, @Param("cc") String content);



}
