package com.shinhan.sbproject.servicefinal;

import com.shinhan.sbproject.entityfinal.WebBoardEntity;
import com.shinhan.sbproject.entityfinal.WebReplyDTO;
import com.shinhan.sbproject.entityfinal.WebReplyEntity;
import com.shinhan.sbproject.repositoryfinal.WebReplyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WebReplyServiceImpl implements WebReplyService {

    final WebReplyRepository replyrepo;

    @Override
    public List<WebReplyDTO> getList(Long bno) {

        ModelMapper mapper = new ModelMapper();
        WebBoardEntity board = WebBoardEntity.builder().bno(bno).build();
        List<WebReplyEntity> replies = replyrepo.findByBoard(board);
        List<WebReplyDTO> dtoList = replies.stream().map(
                entity -> {
                    WebReplyDTO dto = mapper.map(entity, WebReplyDTO.class);
                    return dto;
                }
        ).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public WebReplyDTO selectById(Long rno) {
        WebReplyEntity entity = replyrepo.findById(rno).orElse(null);
        if (entity == null) return null;
        ModelMapper mapper = new ModelMapper();
        WebReplyDTO dto = mapper.map(entity, WebReplyDTO.class);
        dto.setBno(entity.getBoard().getBno());
        return dto;
    }

    @Override
    public int register(WebReplyDTO reply) {
        return f_save(reply);
    }

    @Override
    public int modify(WebReplyDTO reply) {
        return f_save(reply);
    }

    private int f_save(WebReplyDTO reply) {
        ModelMapper mapper = new ModelMapper();

        WebReplyEntity entity = mapper.map(reply, WebReplyEntity.class);

        WebBoardEntity board = WebBoardEntity.builder().bno(reply.getBno()).build();
        entity.setBoard(board);


        WebReplyEntity newEntity = replyrepo.save(entity);
        return newEntity.getRno().intValue();
    }

    @Override
    public int delete(Long rno) {
        replyrepo.deleteById(rno);
        WebReplyEntity entity = replyrepo.findById(rno).orElse(null);
        if (entity == null) return 0;
        return 1;
    }
}
