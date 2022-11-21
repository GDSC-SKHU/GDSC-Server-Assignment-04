package com.example.gdsc5.member;


import lombok.*;
import org.hibernate.validator.constraints.Length;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Length(min = 2, max = 8, message = "2~8자 사이로 하세요")
    private  String username;

    @Length(min = 2, max = 8, message = "2~8자 사이로 하세요")
    private String password;

}
