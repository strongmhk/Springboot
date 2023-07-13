package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

@Data
public class UserUpdateDto {
    // 필수로 받아야 하는 데이터
    private String name;
    private String password;
    // 필수로 받지 않아도 되는 데이터
    private String website;
    private String bio;
    private String phone;
    private String gender;

    // 조금 위험함. 코드 수정이 필요할 예정
    public User toEntity(){
        return User.builder()
                .name(name) // 이름 기재 안했으면 문제!! Validation 체크
                .password(password) // 패스워드 기재 안했으면 문제!! Validation 체크
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
