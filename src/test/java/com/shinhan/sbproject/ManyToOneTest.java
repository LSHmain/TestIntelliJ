package com.shinhan.sbproject;

import com.shinhan.sbproject.entity.MemberEntity;
import com.shinhan.sbproject.entity.MemberRole;
import com.shinhan.sbproject.entity.ProfileEntity;
import com.shinhan.sbproject.repository.MemberRepository;
import com.shinhan.sbproject.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class ManyToOneTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProfileRepository profileRepository;

    //Profile 등록...하나의 Member가 7개의 Profile을 갖는다.
    //Profile을 select + 연결된 Member의 정보를 load 하기 위해 member의 수만큼 merber select 진행
    //N+1 문제 발생
    //Profile이 여러건, Profile의 연결된 member가 현재 3명 존재, Profile을 조회할때 member 테이블을 3번 같이 조회가 되기 시작함
    //이를 해결하기 위해 Repository에서는 @EntityGraph(attributePaths = {"member"})을 통해 findAll을 재정의 해주며 이를 join으로 해결됨
    //@ToString에 Exclude를 꼭 추가해야하는 번거러움 제거
    @Test
    void f_selectAllJoin(){
        profileRepository.findAll().forEach(System.out::println);
    }

    @Test
    void f_profileInsert2() {
        MemberEntity member = memberRepository.findById("4번회원").get();
        if(member == null){
            System.out.println("존재하지 않는 member");
            return;
        }
        IntStream.rangeClosed(1,4).forEach(i -> {
            ProfileEntity profileEntity = ProfileEntity.builder()
                    .fname("cat" + i + ".jpg")
                    .currentYn(i==4?true:false)
                    .member(member)
                    .build();
            profileRepository.save(profileEntity);
        });
    }

    @Test
    void f_profileInsert() {
        MemberEntity member = memberRepository.findById("3번회원").get();
        if(member == null){
            System.out.println("존재하지 않는 member");
            return;
        }
        IntStream.rangeClosed(1,7).forEach(i -> {
            ProfileEntity profileEntity = ProfileEntity.builder()
                    .fname("myFace" + i + ".jpg")
                    .currentYn(i==7?true:false)
                    .member(member)
                    .build();
            profileRepository.save(profileEntity);
        });
    }

    @Test
    void f_profileAll(){
        profileRepository.findAll().forEach(System.out::println);
        //member 정보를 자져오기
//        System.out.println(profileRepository.getMember());
    }

    //member 등록
    @Test
    void f_memberInsert() {
        String[] arr = {"바다", "지민", "윤정", "민혁", "민성", "희찬"};
        IntStream.rangeClosed(0, 5).forEach(i -> {
            MemberEntity member = MemberEntity
                    .builder()
                    .mid(i + "번회원")
                    .mname(arr[i])
                    .mpassword(arr[i] + i)
                    .mrole(i % 2 == 0 ? MemberRole.MANAGER : MemberRole.USER)
                    .build();
            if (i == 6) {
                member.setMrole(MemberRole.ADMIN);
            }
            memberRepository.save(member);

        });
    }

    @Test
    void f_memberAll() {
        memberRepository.findAll().forEach(System.out::println);
    }

    @Test
    void f_memberUpdate() {
        memberRepository.findById("3번회원").ifPresentOrElse(member -> {
                    member.setMname("윤정1");
                    memberRepository.save(member);
                }, () -> {
                    System.out.println("화원이 존재하지 않습니다.");
                }
        );
    }
}
