package com.shinhan.sbproject.servicefinal;

import com.shinhan.sbproject.entityfinal.WebBoardDTO;
import com.shinhan.sbproject.entityfinal.WebBoardEntity;
import com.shinhan.sbproject.repositoryfinal.WebBoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebBoardServiceImpl implements WebBoardService {

    @Autowired
    WebBoardRepository boardRepo;

    @Override
    public List<WebBoardDTO> getList() {
        List<WebBoardEntity> entityList = boardRepo.findAll();
        ModelMapper mapper = new ModelMapper();
        List<WebBoardDTO> dtoList = entityList.stream()
                .map(entity->mapper.map(entity, WebBoardDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public WebBoardDTO selectById(Long bno) {
        return null;
    }

    @Override
    public int register(WebBoardDTO board) {
        return 0;
    }

    @Override
    public int modify(WebBoardDTO board) {
        return 0;
    }

    @Override
    public int delete(Long bno) {
        return 0;
    }

}
