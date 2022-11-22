package com.example.springintroduction.sugang;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SugangRepository {

    public static Map<String, Sugang> sugangMap = new HashMap<>();

    // Map에 <강의명, 강의객체> 로 저장한다.
    public void save(Sugang sugang){
        sugangMap.put(sugang.getSugangname(), sugang);
    }

    // 모든 강의 조회
    public List<Sugang> findAll() {
        Collection<Sugang> values =  sugangMap.values();
        return new ArrayList<>(values);
    }

    // 강의명으로 검색
    public List<Sugang> findBysugangname(String sugangname) {
        return List.of(sugangMap.get(sugangname));
    }

    // 강의 삭제
    public void delete(String sugangname) {
        sugangMap.remove(sugangname);
    }
}
