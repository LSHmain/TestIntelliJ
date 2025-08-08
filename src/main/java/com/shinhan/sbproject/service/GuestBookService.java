package com.shinhan.sbproject.service;

import com.shinhan.sbproject.dto.GuestBookDTO;
import com.shinhan.sbproject.entity.GuestBookEntity;
import com.shinhan.sbproject.repository.GuestBookEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestBookService {
    @Autowired
    GuestBookEntityRepository guestBookEntity;

    public List<GuestBookEntity> selectAll(){
        return (List<GuestBookEntity>) guestBookEntity.findAll();
    }

    public GuestBookEntity dtoTOEntity(GuestBookDTO dto){
        GuestBookEntity entity = GuestBookEntity.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .writer(dto.getWriter())
                .build();

        return entity;

    }
}
