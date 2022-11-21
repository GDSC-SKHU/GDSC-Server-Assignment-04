package com.example.itemgdsc.Member;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {


    @Length(min = 2, max = 8, message = "2~8자 사이로 지정해주세요.")
    private String username;

    @Length(min = 2, max = 8, message = "2~8자 사이로 지정해주세요.")
    private String password;
}
