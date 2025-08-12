package com.shinhan.sbproject;

import com.shinhan.sbproject.entity2.UserCellPhoneEntity;
import com.shinhan.sbproject.entity2.UserCellPhoneEntity2;
import com.shinhan.sbproject.entity2.UserEntity;
import com.shinhan.sbproject.entity2.UserEntity2;
import com.shinhan.sbproject.repository2.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OneToOneTest {
    @Autowired
    UserRepository uRepo;

    @Autowired
    UserCellPhoneRepository ucRepo;

    @Autowired
    UserRepository2 uRepo2;

    @Autowired
    UserCellPhoneRepository2 ucRepo2;


    @Autowired
    UserRepository3 uRepo3;

    @Autowired
    UserCellPhoneRepository3 ucRepo3;

    @Test
    void f_Insert() {
        UserCellPhoneEntity phone = UserCellPhoneEntity.builder()
                .phoneNumber("010-0000-0000")
                .model("apple")
                .build();
        ucRepo.save(phone);

        UserEntity user = UserEntity.builder()
                .userid("ad")
                .username("adc")
                .phone(phone)
                .build();

        uRepo.save(user);
    }


    @Test
    void f_insert2() {
        UserEntity2 user = UserEntity2.builder()
                .userid("kim")
                .username("min")
                .build();
        UserCellPhoneEntity2 phone = UserCellPhoneEntity2.builder()
                .phoneNumber("010-1111-1111")
                .model("iphone")
                .user(user)
                .build();

        ucRepo2.save(phone);
    }

    @Test
    void f_select() {

    }


}
