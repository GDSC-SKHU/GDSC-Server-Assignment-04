package com.example.hyunbok.subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Length(min = 2, max = 8, message = "2~8자 사이로 하세요")
    private String code;

    @Length(min=2, max = 8, message = "2~8자 사이로 하세요우")
    private String name;

    @Length(min = 2, max = 8, message = "2~8자 사이로 하세요")
    private String major;
}
