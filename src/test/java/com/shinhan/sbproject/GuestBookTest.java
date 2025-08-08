package com.shinhan.sbproject;

import com.shinhan.sbproject.entity.GuestBookEntity;
import com.shinhan.sbproject.repository.GuestBookEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class GuestBookTest {

    @Autowired
    GuestBookEntityRepository guestBookEntityRepository;

    @Test
    void f1(){
        IntStream.rangeClosed(1,10).forEach(i->{
            GuestBookEntity book = GuestBookEntity.builder()
                    .title(("book"+1))
                    .writer(("writer"+i))
                    .content(("content"+i))
                    .build();
            guestBookEntityRepository.save(book);
        });

    }


    @Test
    void f2(){
        guestBookEntityRepository.findById(1L).ifPresent(book -> {
            book.setContent("hungry");
            guestBookEntityRepository.save(book);
        });
    }

}
