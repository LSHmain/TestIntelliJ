package com.shinhan.sbproject.repository;

import com.shinhan.sbproject.entity.BoardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//**Repository 설계...Hibernate가 interface를 이용해서 구현
//
//Repository <--- CrudRepository <-- PaginAnsortRepositiry <-- JPARepository
public interface BoardRepository extends
        CrudRepository<BoardEntity, Long>{
    //1. 기본 CRUD 함수는 존재함

    //2. 규칙에 맞는 함수를 정의하여 사용함
    List<BoardEntity> findAllByWriter(String writer);
    List<BoardEntity> findAllByTitleContaining(String title);
    List<BoardEntity> findAllByTitleStartsWith(String title);
//    Page<BoardEntity> findByBnoGreaterThanOrderByBnoAsc(Long bno, Pageable pageable);

}
