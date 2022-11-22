package com.example.gdsc5.text;

import com.example.gdsc5.member.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class TextRepository {

    // DB
    public static Map<String, Text> textMap = new HashMap<>();
    public static Map<String, SubText> subTextMap = new HashMap<>();

    // Member 저장
    public void save(Text text){
        textMap.put(text.getTitle(),text);
    }

    // 모든 글 모두 조회
    public List<Text> findAll1(){
        Collection<Text> values1 = textMap.values();
        return new ArrayList<>(values1);
    }

    public void save2 (SubText subText){
        subTextMap.put(subText.getMyToday(),subText);
    }
    public List<SubText> findAll2(){
        Collection<SubText> values2 = subTextMap.values();
        return new ArrayList<>(values2);
    }


}
