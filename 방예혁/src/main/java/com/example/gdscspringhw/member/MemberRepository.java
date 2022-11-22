package com.example.gdscspringhw.member;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepository {

    // DB
    public static Map<String, Member> memberMap = new HashMap<>();

    // Member 저장
    public void save(Member member) {
        memberMap.put(member.getUsername(), member);
    }

    // Member 정보 변경
    public static void replace(Member member, Member changedMember) {
        memberMap.replace(member.getUsername(), changedMember);
    }

    // 멤버 모두 조회
    public List<Member> findAll() {
        Collection<Member> values = memberMap.values();
        return new ArrayList<>(values);
    }

    // 이름으로 찾기
    public List<Member> findByUsername(String username) {
        return List.of(memberMap.get(username));
    }

    // 프리미엄 구독 여부로 찾기
    public List<Member> findByPremium() {
        // 전체 회원 정보
        Collection<Member> values = memberMap.values();

        // 그 중 프리미엄에 구독한 회원만 리스트에 담아 리턴
        List<Member> premiumMember = new ArrayList<>();
        for(Member member : values) {
            if(member.getPremium() == true) {
                premiumMember.add(member);
            }
        }
        return premiumMember;
    }

}
