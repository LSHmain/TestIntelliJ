package com.shinhan.sbproject;


import com.shinhan.sbproject.entity2.MultiKeyB;
import com.shinhan.sbproject.entity2.MultikeyAEntity;
import com.shinhan.sbproject.entity2.MultikeyBEntity;
import com.shinhan.sbproject.repository2.MultikeyARepository;
import com.shinhan.sbproject.repository2.MultikeyBRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MultikeyTest {

    @Autowired
    MultikeyARepository aRepo;

    @Autowired
    MultikeyBRepository bRepo;

    @Test
    void f_insertA(){
        MultikeyAEntity obj1 = MultikeyAEntity.builder()
                .id1(1)
                .id2(1)
                .name("name"+1)
                .build();

        MultikeyAEntity obj2 = MultikeyAEntity.builder()
                .id1(1)
                .id2(2)
                .name("name"+2)
                .build();

        aRepo.save(obj1);
        aRepo.save(obj2);
    }


    @Test
    void f_insertB(){
        MultiKeyB newid1 = MultiKeyB.builder().id1(1).id2(1).build();
        MultiKeyB newid2 = MultiKeyB.builder().id1(1).id2(2).build();
        MultikeyBEntity obj1 = MultikeyBEntity.builder()
                .id(newid1)
                .name("name"+1)
                .build();

        MultikeyBEntity obj2 = MultikeyBEntity.builder()
                .id(newid2)
                .name("name"+2)
                .build();

        bRepo.save(obj1);
        bRepo.save(obj2);
    }
}
