package com.example.shoppingmall.repository;

import com.example.shoppingmall.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class LoginRepository {
    //멤버 저장소
    public static Map<String, Member> store = new HashMap<>();
    //memberNo 자동 부여
    private static long sequence = 0L;

    //멤버 등록
    public void save(Member member) {
        member.setMemberNo(++sequence);
        store.put(member.getMemberId(), member);
    }

    //멤버 조회
    public Member findById(Member member) {
        return store.get(member.getMemberId());
    }
}
