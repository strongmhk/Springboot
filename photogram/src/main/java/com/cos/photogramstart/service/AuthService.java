package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service // 1.IoC 2.트랜잭션 관리
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional // Write(Insert, Update, Delete), 이 함수가 실행돼서 종료될 때까지 트랜잭션을 관리해줌
    public User 회원가입(User user){ // 외부에서 통신을 통해 받은 데이터를 user 객체에 담음(form에 입력받은 데이터 파라미터로 받아오기)
        // 회원가입 진행
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER"); //관리자 ROLE_ADMIN
        User userEntity = userRepository.save(user); // database에 있는 데이터를 userEntity에 담음
        return userEntity;
    }
}
