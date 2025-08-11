package com.shinhan.sbproject;

import com.querydsl.core.BooleanBuilder;
import com.shinhan.sbproject.entity2.FreeBoardEntity;
import com.shinhan.sbproject.entity2.FreeReplyEntity;
import com.shinhan.sbproject.entity2.QFreeBoardEntity;
import com.shinhan.sbproject.repository2.FreeBoardRepository;
import com.shinhan.sbproject.repository2.FreeReplyRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


@SpringBootTest
public class BIDirectionTest {

    @Autowired
    FreeBoardRepository boardRepo;

    @Autowired
    FreeReplyRepository replyRepo;

    //1. board만 저장하기(10건)
    @Test
    void f_boardInsert(){
        IntStream.rangeClosed(1,10).forEach(i -> {
            FreeBoardEntity fb = FreeBoardEntity.builder()
                    .title("title"+i)
                    .content("content"+i)
                    .writer("writer"+i%5)
                    .build();
            boardRepo.save(fb);
        });
    }

    //2. board 입력시(5건) Reply(3건)도 입력
    @Test
    void f_replyInsert(){
        IntStream.rangeClosed(100,105).forEach(i -> {
            FreeBoardEntity board = FreeBoardEntity.builder()
                    .title("title+"+i)
                    .content("content+"+i)
                    .writer("writer+"+i%5)
                    .build();
            List<FreeReplyEntity> replyList = new ArrayList<>();
            IntStream.rangeClosed(100,103).forEach(j -> {
                FreeReplyEntity reply = FreeReplyEntity.builder()
                        .reply("reply"+j)
                        .replyer("replyer"+j%5)
                        .board(board)
                        .build();
                replyList.add(reply);
            });
            board.setReplies(replyList);
            boardRepo.save(board);
        });
    }

    @Test
    void f_choiceInsert(){
       Long bno = 23L;
       FreeBoardEntity board = boardRepo.findById(bno).orElse(null);
       if(board==null){
           System.out.println("존재하지 않는 게시물 입니다.");
           return;
       }
        List<FreeReplyEntity> replyList = board.getReplies();
        IntStream.rangeClosed(1,3).forEach(j -> {
            FreeReplyEntity reply = FreeReplyEntity.builder()
                    .reply("ㅁㄴㅇㄴㅁㅇ"+j)
                    .replyer("ㄴㅁㅇㅁㄴㅇ"+j%5)
                    .board(board)
                    .build();
            replyList.add(reply);
        });
        board.setReplies(replyList);
        boardRepo.save(board);

    }

    //댓글 모두 지우기
    @Test
    void f_repliesDelete(){
        replyRepo.deleteAll();
//        boardRepo.deleteAll();
    }

    @Test
    void f_selectAll2(){
        boardRepo.findAll().forEach(board -> {
            System.out.println(board);
        });
    }


    @Test
    void f_selectAll() {
        boardRepo.findAll().forEach(board -> {
            System.out.println(board+"--댓글수: " + board.getReplies().size());

        });
        System.out.println("-----------");
        boardRepo.findAll2().forEach(board -> {
            System.out.println(board+"--댓글수: " + board.getReplies().size());

        });
    }

    @Test
    void f_selectAll3() {
        boardRepo.getBoardInfo().forEach(board -> {
            System.out.println(Arrays.toString(board));
        });
    }
    @Transactional
    @Test
    void f_selectDetail(){
        Long bno = 26L;
        FreeBoardEntity board = boardRepo.findById(bno).orElse(null);
        if(board==null){
            System.out.println("존재하지 않는 번호입니다.");
        }

        List<FreeReplyEntity> boardList = board.getReplies();
        boardList.forEach(i -> {
            System.out.println(i);
        });

    }


    @Transactional
    @Test
    void f_dynamicSQL(){
        String type = "title";
        String keyword = "title+";
        BooleanBuilder builder = new BooleanBuilder();
        QFreeBoardEntity board = QFreeBoardEntity.freeBoardEntity;
        switch (type){
            case "title": {builder.and(board.title.like("%"+keyword+"%"));}
            case "writer": {builder.and(board.title.like("%"+keyword+"%"));}
            case "content": {builder.and(board.title.like("%"+keyword+"%"));}

        }
        builder.and(board.id.gt(0L));

        Pageable page = PageRequest.of(0,2);
        Page<FreeBoardEntity> result =  boardRepo.findAll(builder, page);
        List<FreeBoardEntity> blist = result.getContent();
        blist.forEach(b -> {
            System.out.println(b);
        });
        System.out.println("-----------------------");
        System.out.println("getNumber: "+result.getNumber());
        System.out.println("getSize: "+result.getSize());
        System.out.println("getTotalPages: "+result.getTotalPages());
        System.out.println("getTotalElements: "+result.getTotalElements());
    }
}
