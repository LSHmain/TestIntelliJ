package com.shinhan.sbproject.repository2;

import com.shinhan.sbproject.entity2.PDSBoardEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PDSBoardRepository extends JpaRepository<PDSBoardEntity, Long> {
    //1) N+1 문제 해결
    @EntityGraph(attributePaths = "files2")
    List<PDSBoardEntity> findAll();

    @Query("""
            SELECT board
            FROM #{#entityName} board
            JOIN FETCH board.files2           
            """)
    List<PDSBoardEntity> findAll2();

    //JPQL 작성: board별 File의 count
    //nativeQuery에서 count(*)의 의미는 null 포함한 개수
    //nativeQuery에서 count(칼럼이름)의 의미는 null 제외한 개수

    @Query("""
            SELECT board.pid
            FROM #{#entityName} board
            JOIN board.files2
            GROUP BY board.pid
            """)
    List<Object[]> getFileCount3();

    @Query("select board.pid, count(file.fno) "
            + " from #{#entityName} board left outer join board.files2 file "
            + " group by board.pid")
    List<Object[]> getFileCount();


    @Query(value = "select board.pid, count(file.pdsno) "
            + " from t_pdsboard board left outer join t_pdsfile file on(board.pid = file.pdsno) "
            + " group by board.pid", nativeQuery = true)
    List<Object[]> getFileCount2();

    //@Query를 이용해서 DML 사용
    //fno, fname을 받아서 update를 하고자 한다.
    @Modifying
    @Query("UPDATE PDSFileEntity SET pdsfilename = :fname WHERE fno = :fno")
    int updatePdsFile(@Param("fname")  String newFileName,@Param("fno") Long newFno);

}
