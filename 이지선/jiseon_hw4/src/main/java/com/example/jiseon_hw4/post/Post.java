package com.example.jiseon_hw4.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Length(min = 1, max = 100, message = "제목을 입력해주세요.")
    private String postname;
    @Length(min = 1, message = "내용을 입력해주세요.")
    private String contents;
}
