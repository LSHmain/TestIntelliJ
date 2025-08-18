package com.shinhan.sbproject;

import com.querydsl.core.types.Predicate;
import com.shinhan.sbproject.entityfinal.WebBoardDTO;
import com.shinhan.sbproject.entityfinal.WebBoardEntity;
import com.shinhan.sbproject.entityfinal.WebReplyEntity;
import com.shinhan.sbproject.paging.PageRequestDTO;
import com.shinhan.sbproject.paging.PageResultDTO;
import com.shinhan.sbproject.repositoryfinal.WebBoardRepository;
import com.shinhan.sbproject.repositoryfinal.WebReplyRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
public class WebBoardTest {

    /*
     *
     * tbl_webboard: 10건 입력
     * tbl_webboard(20건) + tbl_webreply(5건) 입력
     *
     * */

    @Autowired
    WebBoardRepository wbRepo;

    @Autowired
    WebReplyRepository wrRepo;
    @Autowired
    private RestClient.Builder builder;


    @Test
    void f_insert() {
        wrRepo.deleteAll();
        wbRepo.deleteAll();

        IntStream.rangeClosed(1, 20).forEach(i -> {
            WebBoardEntity board = WebBoardEntity.builder()
                    .title("title" + i)
                    .writer("write" + i)
                    .content("content" + i)
                    .build();

            List<WebReplyEntity> replies = new ArrayList<>();
            IntStream.rangeClosed(1, 5).forEach(j -> {
                WebReplyEntity rep = WebReplyEntity.builder()
                        .replyText("replyText" + j)
                        .replyer("replayer" + j)
                        .board(board)
                        .build();
                replies.add(rep);
            });

            board.setReplies(replies);
            wbRepo.save(board);
        });
    }

    @Test
    void selectAll() {
        List<WebBoardEntity> boardList = wbRepo.findAll();
        ModelMapper mapper = new ModelMapper();
        List<WebBoardDTO> dtoList = boardList.stream().map(entity -> {
            WebBoardDTO dt = mapper.map(entity, WebBoardDTO.class);
            dt.setReplyCount(2L);
            return dt;
        }).collect(Collectors.toList());

        dtoList.forEach(dto -> {
            System.out.println(dto);
        });
    }

//    @Test
//    void f_replyFindTest() {
//        wbRepo.findAll2().forEach(board -> System.out.println(board));
//    }


    WebBoardDTO entityToDTO(WebBoardEntity entity){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, WebBoardDTO.class);
    }

//    @Test
//    void f_paging(){
//
//        String type= "t";
//        String keyword = "spring";
//        PageRequestDTO pageDTO = PageRequestDTO.builder()
//                .page(1)
//                .size(5)
//                .type(type)
//                .keyword(keyword)
//                .build();
//
//        Sort sort = Sort.by(Sort.Direction.ASC, "bno");
//        Pageable pageable = pageDTO.getPageable(sort);
//        Predicate predicate = wbRepo.makePredicate(type, keyword);
//        Page<WebBoardEntity> result = wbRepo.findAll(predicate, pageable);
//        Function<WebBoardEntity, WebBoardDTO> fn = entity -> entityToDTO(entity);
//        PageResultDTO<WebBoardDTO, WebBoardEntity> responseResult = new PageResultDTO<>(result,fn);
////        Function<WebBoardEntity, WebBoardDTO> fn = this::entityToDTO;
////        PageResultDTO<WebBoardDTO, WebBoardEntity> responseResult = new PageResultDTO<>(result, fn);
//        System.out.println(responseResult.getDtoList()); // () 빠졌었음
//        System.out.println(responseResult.getStart());
//        System.out.println(responseResult.getEnd());
//
//
//    }

}
