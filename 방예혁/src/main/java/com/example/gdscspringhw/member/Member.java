package com.example.gdscspringhw.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter // lombok, getter and setter 가 있는 상태임
@Getter
@NoArgsConstructor // 아무것도 안받는 생성자가 있다는 말
@AllArgsConstructor // 모든 필드를 받는 생성자가 있다는 말
public class Member {

    @Length(min = 2, max = 8, message = "2~8자 사이로 하세요")
    private String username;

    @Length(min = 2, max = 8, message = "2~8자 사이로 하세요")
    private String password;

    private Boolean premium; // 회원의 프리미엄 서비스 구독 여부, ex) 유튜브 프리미엄
}
