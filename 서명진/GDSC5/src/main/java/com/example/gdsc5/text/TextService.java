package com.example.gdsc5.text;

import com.example.gdsc5.member.Member;
import com.example.gdsc5.member.MemberRepository;
import com.example.gdsc5.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TextService {

    private final TextRepository textRepository;

    public void save1(Text text ){
        textRepository.save(text);
    }

    //전체 조회
    public List<Text> findAll1(){
        return textRepository.findAll1();
    }

    // 이름으로 단건 조회
    public List<Text> findByTitle(String title){
        return textRepository.findByTitle(title);
    }

}
