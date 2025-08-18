package com.shinhan.sbproject.servicefinal;

import com.querydsl.core.types.Predicate;
import com.shinhan.sbproject.entityfinal.WebBoardDTO;
import com.shinhan.sbproject.entityfinal.WebBoardEntity;
import com.shinhan.sbproject.entityfinal.WebReplyEntity;
import com.shinhan.sbproject.paging.PageRequestDTO;
import com.shinhan.sbproject.paging.PageResultDTO;
import com.shinhan.sbproject.repositoryfinal.WebBoardRepository;
import com.shinhan.sbproject.repositoryfinal.WebReplyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class WebBoardServiceImpl implements WebBoardService {

    @Autowired
    WebBoardRepository boardRepo;

    @Autowired
    WebReplyRepository replyRepo;

    @Override
    public List<WebBoardDTO> getList() {
        List<WebBoardEntity> entityList = boardRepo.findAll();
        ModelMapper mapper = new ModelMapper();
        List<WebBoardDTO> dtoList = entityList.stream()
                .map(entity->mapper.map(entity, WebBoardDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    WebBoardDTO entityToDTO(WebBoardEntity entity){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, WebBoardDTO.class);
    }

    @Override
    public PageResultDTO<WebBoardDTO, WebBoardEntity> getList(PageRequestDTO pageDTO) {

        Sort sort = Sort.by(Sort.Direction.ASC, "bno");
        Pageable pageable = pageDTO.getPageable(sort);
        Predicate predicate = boardRepo.makePredicate(pageDTO.getType(), pageDTO.getKeyword());
        Page<WebBoardEntity> result = boardRepo.findAll(predicate, pageable);
        Function<WebBoardEntity, WebBoardDTO> fn = entity -> entityToDTO(entity);
        PageResultDTO<WebBoardDTO, WebBoardEntity> responseResult = new PageResultDTO<>(result, fn);
        return null;
    }


    @Override
    public WebBoardDTO selectById(Long bno) {
        WebBoardEntity entity = boardRepo.findById(bno).orElse(null);
        if(entity == null) return null;

        ModelMapper mapper = new ModelMapper();
        WebBoardDTO dto = mapper.map(entity, WebBoardDTO.class);

        List<WebReplyEntity> replies = replyRepo.findByBoard(entity);
        dto.setReplyCount(Long.valueOf(replies.size()));
        return dto;
    }

    @Override
    public int register(WebBoardDTO board) {
        ModelMapper mapper = new ModelMapper();
        WebBoardEntity dto = mapper.map(board, WebBoardEntity.class);
        boardRepo.save(dto);
        return 1;
    }

    @Override
    public int modify(WebBoardDTO board) {
        ModelMapper mapper = new ModelMapper();
        WebBoardEntity dto = mapper.map(board, WebBoardEntity.class);
        boardRepo.save(dto);
        return 1;
    }

    @Override
    public int delete(Long bno) {
        WebBoardEntity board = boardRepo.findById(bno).orElse(null);
        boardRepo.delete(board);
        return 1;
    }

    @Override
    public List<WebBoardDTO> selectAll() {
        List<WebBoardEntity> entityList = boardRepo.findAll();
        ModelMapper mapper = new ModelMapper();
        return entityList.stream()
                .map(entity -> mapper.map(entity, WebBoardDTO.class))
                .collect(Collectors.toList());
    }


}
