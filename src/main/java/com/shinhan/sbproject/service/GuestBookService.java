package com.shinhan.sbproject.service;

import com.shinhan.sbproject.dto.GuestBookDTO;
import com.shinhan.sbproject.entity.GuestBookEntity;
import com.shinhan.sbproject.repository.GuestBookEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestBookService {
    @Autowired
    GuestBookEntityRepository guestBookEntity;

    public List<GuestBookDTO> selectAll(){
        List<GuestBookEntity> booklist = (List<GuestBookEntity>) guestBookEntity.findAll();

        List<GuestBookDTO> booklist2 = booklist.stream()
                .map(entity -> entityTODTO(entity))
                .collect(Collectors.toList());

        return booklist2;
    }

    public GuestBookEntity dtoTOEntity(GuestBookDTO dto){
        GuestBookEntity entity = GuestBookEntity.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .writer(dto.getWriter())
                .build();

        return entity;

    }
    public GuestBookDTO entityTODTO(GuestBookEntity entity){
        GuestBookDTO dto = GuestBookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .writer(entity.getWriter())
                .content(entity.getContent())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }
}
