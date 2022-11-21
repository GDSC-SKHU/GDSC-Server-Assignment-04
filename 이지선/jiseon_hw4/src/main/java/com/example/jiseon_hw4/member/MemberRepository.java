package com.example.jiseon_hw4.member;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepository {

    // DB 연결 아직 안배웠으므로 DB처럼 쓸 임시 DB
    public static Map<String, Member> memberMap = new HashMap<>();

    // Member 저장
    public void save(Member member) {
        memberMap.put(member.getUsername(), member);
    }

}
