package com.shinhan.sbproject;


import com.shinhan.sbproject.entity2.UserCellPhoneEntity;
import com.shinhan.sbproject.entity2.UserEntity;
import com.shinhan.sbproject.repository2.UserCellPhoneRepository;
import com.shinhan.sbproject.repository2.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OneToOneTest {
    @Autowired
    UserRepository uRepo;

    @Autowired
    UserCellPhoneRepository ucRepo;

    @Test
    void f_Insert(){
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
}
