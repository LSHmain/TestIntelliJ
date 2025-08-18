package com.shinhan.sbproject.servicefinal;

import com.shinhan.sbproject.dto.SecurityUser;
import com.shinhan.sbproject.entity.MemberEntity;
import com.shinhan.sbproject.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepo;

    @Autowired
    HttpSession httpSession;

    @Autowired
    PasswordEncoder passwordEncoder;


    //***사용자등록로직 추가하기
    public MemberEntity joinUser(MemberEntity member) {
        member.setMpassword(passwordEncoder.encode(member.getMpassword()));
        return memberRepo.save(member);
    }

    //필수인 메서드임(로그인시 Spring이 사용함)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("========loadUserByUsername:" + username + "==============");
        Optional<MemberEntity> memberOp = memberRepo.findById(username);
        MemberEntity member = memberOp.orElse(null);

        //MemberEntity ==> SecurityUser 변경
        UserDetails user = memberOp.filter(m->m!= null).map(m->new SecurityUser(member)).get();
        httpSession.setAttribute("loginMember", member);
        return user;
    }

}

