package com.shinhan.sbproject;

import com.shinhan.sbproject.entity2.PDSBoardEntity;
import com.shinhan.sbproject.entity2.PDSFileEntity;
import com.shinhan.sbproject.repository2.PDSBoardRepository;
import com.shinhan.sbproject.repository2.PDSfileRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Commit
@SpringBootTest
public class OneToMany {

    @Autowired
    PDSBoardRepository pdsBoardRepository;

    @Autowired
    PDSfileRepository pdsfileRepository;

    //Board를 통해서 File을 저장하기
    @Test
    void f_insert() {
        //board 5건 만들기
        IntStream.rangeClosed(1, 5).forEach(i -> {
            PDSBoardEntity board = PDSBoardEntity.builder()
                    .pname("name" + i)
                    .pwriter("writer" + i)
                    .build();
            List<PDSFileEntity> filesList = new ArrayList<>();
            IntStream.rangeClosed(1, 3).forEach(j -> {
                PDSFileEntity file = PDSFileEntity.builder()
                        .pdsfilename("filename" + j + ".png")
                        .build();
                filesList.add(file);
            });
            board.setFiles2(filesList);
            pdsBoardRepository.save(board);
        });
    }


    //board 조회
    //1) LAZY 인데 이 업무에서 연관관계 table도 조회하고자 한다.
    //@Transactional
    //2)fetch = FetchType.EAGER
    //3)N+1 믄제 해결 EntityGraph 이용
    @Test
    void f_select() {
        pdsBoardRepository.findAll().forEach(System.out::println);
        System.out.println("------------------------------------");
        pdsBoardRepository.findAll2().forEach(System.out::println);
    }


    @Test
    void f_update() {
        Long pid = 1L;
        pdsBoardRepository.findById(pid).ifPresent(board -> {
            PDSFileEntity file = PDSFileEntity.builder()
                    .pdsfilename("coffee" + pid + ".png")
                    .build();

            board.setPname("name+" + pid);
            board.getFiles2().add(file);
            pdsBoardRepository.save(board);
        });
    }

    @Test
    void f_update2() {
        PDSFileEntity file = pdsfileRepository.findById(1L).orElse(null);
        if (file != null) {
            return;
        }
        pdsBoardRepository.findById(1L).ifPresent(board -> {
            board.getFiles2().add(file);
            pdsBoardRepository.save(board);
        });
    }

    @Test
    void f_boardInsert(){
        PDSBoardEntity board = PDSBoardEntity.builder()
                .pname("name+Spring" + 1)
                .pwriter("writer+Spring" + 1)
                .build();
        pdsBoardRepository.save(board);
    }

    @Test
    void f_selectFileCount(){
        pdsBoardRepository.findAll().forEach(board -> {
            System.out.println("bno: " + board.getPid());
            System.out.println("file count: "+board.getFiles2().size());

        });
        System.out.println("-------------");
        pdsBoardRepository.getFileCount().forEach(System.out::println);
    }


    @Transactional
    @Test
    void f_fileUpdate(){
        String newFileName = "abc";
        Long newFno = 1L;
        int result = pdsBoardRepository.updatePdsFile(newFileName, newFno);
        System.out.println(result+"success");
    }

    @Transactional
    @Test
    void f_boardUpdate(){
        Long pid = 1L;
        pdsBoardRepository.findById(pid).ifPresent(board -> {
            board.setPname("name" + pid);
            board.setPwriter("writer" + pid);
            List<PDSFileEntity> flist = board.getFiles2();
            flist.removeIf(f -> f.getPdsfilename().startsWith("abc"));

            PDSFileEntity f1 = PDSFileEntity.builder()
                    .pdsfilename("wow" + pid + ".png")
                    .build();
            PDSFileEntity f2 = PDSFileEntity.builder()
                    .pdsfilename("yee" + pid + ".png")
                    .build();
            flist.add(f1);
            flist.add(f2);

            System.out.println(flist);

            pdsBoardRepository.save(board);
        });
    }

}

