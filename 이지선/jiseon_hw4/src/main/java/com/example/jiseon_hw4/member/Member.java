package com.example.jiseon_hw4.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Length(min=2, max=8, message = "2~8자 사이로 입력하세요.")
    private String username;
    @Length(min=2, max=8, message = "2~8자 사이로 입력하세요.")
    private String password;
}