package com.shinhan.sbproject.repositoryfinal;

import com.querydsl.core.BooleanBuilder;
//import com.shinhan.sbproject.entityfinal.QWebBoardEntity;
//import com.shinhan.sbproject.entitytotal.entityfinal.QWebBoardEntity;
import com.shinhan.sbproject.entityfinal.QWebBoardEntity;
import com.shinhan.sbproject.entityfinal.WebBoardEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;


public interface WebBoardRepository extends JpaRepository<WebBoardEntity,Long> , QuerydslPredicateExecutor<WebBoardEntity> {

    @EntityGraph(attributePaths = "replies")
//    @Query("select b from WebBoardEntity b")
    List<WebBoardEntity> findAll();



    @Query("""
        SELECT board
        FROM WebBoardEntity board
        JOIN FETCH board.replies
    """)
    List<WebBoardEntity> findAll2();

    public default Predicate makePredicate(String type, String keyword) {
        QWebBoardEntity board = QWebBoardEntity.webBoardEntity;
        BooleanBuilder builder = new BooleanBuilder();
        if (type == null)
            return builder;
        if (type.contains("t")) {
            builder.or(board.title.contains(keyword));
        }
        if (type.contains("c")) {
            builder.or(board.content.contains(keyword));
        }
        if (type.contains("w")) {
            builder.or(board.writer.eq(keyword));
        }
        return builder;

    }


}
