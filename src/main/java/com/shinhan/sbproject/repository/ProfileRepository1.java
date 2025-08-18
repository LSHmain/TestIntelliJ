package com.shinhan.sbproject.repository;

import com.shinhan.sbproject.entity.MemberEntity;
import com.shinhan.sbproject.entity.ProfileEntity1;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository1 extends JpaRepository<ProfileEntity1, Long> {
    //1)기존함수 재정의
    // @Query없이 기존 함수를 재정의 한다.
    @EntityGraph(attributePaths = {"member"})
    //연관된 속성을 즉시 가져올지 명시, fetch join으로 생성되어 N+1 문제를 해결해 준다.
    List<ProfileEntity1> findAll();


    //2) fetch join을 진행
    //@Query를 통한 정의 진행
    @Query("select p from ProfileEntity1 p join fetch p.member")
    List<ProfileEntity1> findAll2();

    List<ProfileEntity1> findByMember(MemberEntity member);

    List<ProfileEntity1> findAllByMember(MemberEntity member);

    List<ProfileEntity1> findAllByMemberMid(String mid);


    @Query("""
                SELECT p.member, COUNT(p)
                FROM ProfileEntity1 p
                JOIN p.member m
                GROUP BY m.mid
                ORDER BY m.mid
            """)
    List<Object[]> getMemberProfileCount();


    @Query(value = """
                SELECT p.member_mid, COUNT(*)
                FROM t_profile p
                JOIN t_member m ON p.member_mid = m.mid
                GROUP BY mid
                ORDER BY m.mid
            """, nativeQuery = true)
    List<Object[]> getMemberProfileCount2();

    List<ProfileEntity1> countProfileEntitiesByMember_Mid(String mid);
    long countByMember_Mid(String mid);

    @EntityGraph(attributePaths = {"member"})
    List<ProfileEntity1> findByCurrentYn(boolean currentYn);


    

}
