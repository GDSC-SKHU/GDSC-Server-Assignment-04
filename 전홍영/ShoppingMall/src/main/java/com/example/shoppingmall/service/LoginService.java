package com.example.shoppingmall.service;

import com.example.shoppingmall.domain.Member;
import com.example.shoppingmall.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    //회원 저장
    // 회원 비밀번호 암호화하여 비밀번호 재설정후 저장
    public void save(Member member) {
        String encoded = passwordEncoder.encode(member.getMemberPassword());
        member.setMemberPassword(encoded);
        loginRepository.save(member);
    }
    //회원 조회
    public Member findById(Member member) {
        return loginRepository.findById(member);
    }
    //회원 검증
    public void validate(Member member) {
        Member foundMember = loginRepository.store.get(member.getMemberId());
        if ( !passwordEncoder.matches(member.getMemberPassword(), foundMember.getMemberPassword())){
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
    }

}
