package com.shinhan.sbproject.repositoryfinal;

import com.shinhan.sbproject.entity.MemberEntity;
import com.shinhan.sbproject.entityfinal.ProfileEntity;
import com.shinhan.sbproject.entityfinal.ProfileEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository extends JpaRepository<com.shinhan.sbproject.entityfinal.ProfileEntity,Long> {
    
    // @Query 없이 기존 함수를 재정의
    @EntityGraph(attributePaths = {"member"}) // 연관된 속성을 즉시 가져올지 명시, fetch join으로 생성되어 N+1문제를 해결
    List<ProfileEntity> findAll();

    // fetch join 이용하기
    @Query("select p from #{#entityName} p join fetch p.member")
    List<ProfileEntity> findAll2();

    @EntityGraph(attributePaths = {"member"})
    List<ProfileEntity> findByMember(MemberEntity member);

    @EntityGraph(attributePaths = {"member"})
    List<ProfileEntity> findByMemberMid(String mids);

    // JPQL 작성하기
    @Query("select count(p) from #{#entityName} p group by p.member.mid")
    List<Long> countProfileByMember();

    // [LAB] currentYn값으로 Profile을 select하기
    @EntityGraph(attributePaths = {"member"})
    List<ProfileEntity> findByCurrentYn(boolean currentYn);

    // [LAB] 특정 Member의 currentYn값으로 Profile을 select하기
    @EntityGraph(attributePaths = {"member"})
    List<ProfileEntity> findByCurrentYnAndMember(boolean currentYn, MemberEntity member);

    void findByFname(String fileName);

    List<ProfileEntity> findProfileEntityByFname(String fname);
}
