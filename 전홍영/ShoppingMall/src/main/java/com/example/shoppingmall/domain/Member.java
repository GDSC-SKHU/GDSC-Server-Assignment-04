package com.example.shoppingmall.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {

    private Long memberNo;
    private String memberName;
    private String memberId;
    private String memberPassword;

    public Member(String memberName, String memberId, String memberPassword) {
        this.memberName = memberName;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
    }
}
