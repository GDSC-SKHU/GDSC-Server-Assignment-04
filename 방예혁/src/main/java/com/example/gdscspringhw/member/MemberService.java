package com.example.gdscspringhw.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // Member 객체 저장
    public void save(Member member) {
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        memberRepository.save(member);
    }

    // 멤버 전체 조회
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    // 이름으로 단건 조회
    public List<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    // 프리미엄 구독 여부로 조회
    public List<Member> findByPremium() {
        return memberRepository.findByPremium();
    }

    // 로그인 시도한 회원정보 검즘
    public void validate(Member member) {
        Member foundMember = MemberRepository.memberMap.get(member.getUsername());

        // 사용자가 입력한 비밀번호가 암호화된 DB상 비밀번호와 일치하는지 검사
        if(!passwordEncoder.matches(member.getPassword(), foundMember.getPassword())) {
            throw new IllegalArgumentException("비밀번호 틀림");
        }
    }

    // 로그인 시도한 회원의 프리미엄 구독 여부
    public Boolean checkPremium(Member member) {
        Member foundMember = MemberRepository.memberMap.get(member.getUsername());

        return foundMember.getPremium();
    }

    // 일반 회원이 프리미엄에 가입하기
    public void joinPremium(Member member) {
        // 매개변수로 받은 회원 정보를 mb 복사하기
        Member mb = member;
        // 복사한 mb premium 값을 true로 바꿔서 가입시킴
        mb.setUsername(member.getUsername());
        mb.setPremium(true);
        mb.setPassword(member.getPassword());
        // replace로 정보를 변경
        MemberRepository.replace(member, mb);
    }

    /* @RequiredArgsConstructor 를 이용해서 안써도 존재함
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */
}
