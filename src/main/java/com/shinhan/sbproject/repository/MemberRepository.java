package com.shinhan.sbproject.repository;

import com.shinhan.sbproject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {


}
