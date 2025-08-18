package com.shinhan.sbproject.repositoryfinal;

import com.shinhan.sbproject.entityfinal.WebBoardEntity;
import com.shinhan.sbproject.entityfinal.WebReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebReplyRepository extends JpaRepository<WebReplyEntity, Long> {
    List<WebReplyEntity> findByBoard(WebBoardEntity board);
}
