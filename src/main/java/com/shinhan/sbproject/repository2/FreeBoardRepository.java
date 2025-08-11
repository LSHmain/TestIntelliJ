package com.shinhan.sbproject.repository2;

import com.querydsl.core.types.Predicate;
import com.shinhan.sbproject.entity2.FreeBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface FreeBoardRepository extends JpaRepository<FreeBoardEntity, Long>,
        QuerydslPredicateExecutor<FreeBoardEntity> {

    @EntityGraph(attributePaths = "replies")
    List<FreeBoardEntity> findAll();

    @Query("""
            SELECT  board
            FROM #{#entityName} board
            JOIN FETCH board.replies
            """)
    List<FreeBoardEntity> findAll2();


    @Query("""
            SELECT fboard.id, COUNT (replay)
            FROM FreeBoardEntity fboard
            LEFT OUTER JOIN fboard.replies replay
            GROUP BY fboard.id
            """)
    List<Object[]> getBoardInfo();


    @EntityGraph(attributePaths = "replies")
    Page<FreeBoardEntity> findAll(Predicate builder, Pageable page);


}
