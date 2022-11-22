package com.example.gdsc5.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(Member member){
        String encodedPassword =  passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);

        memberRepository.save(member);
    }

    //전체 조회
    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    // 이름으로 단건 조회
    public List<Member> findByUsername(String username){
        return memberRepository.findByUsername(username);
    }

    // 로그인 시도한 회원정보 검증
    public void validate(Member member){
        Member foundMember =MemberRepository.memberMap.get(member.getUsername());
        // 사용자가 입력한 비밀번호가 암호화된 DB상 비밀번호와 일치하는지 검사
        if(!passwordEncoder.matches(member.getPassword(),foundMember.getPassword())){
            throw new IllegalArgumentException("비밀번호 틀림");
        }
    }


//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }


}
