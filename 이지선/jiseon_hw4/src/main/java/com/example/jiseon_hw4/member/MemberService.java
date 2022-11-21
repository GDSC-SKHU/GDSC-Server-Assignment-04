package com.example.jiseon_hw4.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(Member member) {
        // 패스워드 암호화 처리
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);

        // Member 객체 DB에 저장
        memberRepository.save(member);
    }

    // 로그인 시도한 회원정보 검증
    public void validate(Member member) {
        Member foundMember = MemberRepository.memberMap.get(member.getUsername());

        // 사용자가 입력한 비밀번호가 암호화된 DB상 비밀번호와 일치하는지 검사
        if (!passwordEncoder.matches(member.getPassword(), foundMember.getPassword())){
            throw new IllegalArgumentException("비밀번호 틀림");
        }
    }
}
