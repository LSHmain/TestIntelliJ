package com.shinhan.sbproject;

import com.shinhan.sbproject.entity.MemberRole;
import com.shinhan.sbproject.entity2.EnumTypeEntity;
import com.shinhan.sbproject.repository2.EnumTypeRepoesitory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class EnumTest {

    @Autowired
    EnumTypeRepoesitory eRepo;


    @Test
    void f_enumInstert(){
        Set<MemberRole>  mroleSet = new HashSet<>();
        mroleSet.add(MemberRole.ADMIN);
        mroleSet.add(MemberRole.USER);
        mroleSet.add(MemberRole.MANAGER);

        EnumTypeEntity enumtype = EnumTypeEntity.builder()
                .mid("회원1")
                .mname("회원1이름")
                .mpassword("회원1비밀번호")
                .mrole(mroleSet)
                .build();

        eRepo.save(enumtype);
    }
}
