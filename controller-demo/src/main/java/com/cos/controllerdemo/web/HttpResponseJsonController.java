package com.cos.controllerdemo.web;

import com.cos.controllerdemo.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpResponseJsonController {

    @GetMapping("/resp/json")
    public String respJson(){
        return "{\"username\":\"cos\"}";
    }

    @GetMapping("/resp/json/javaobject")
    public User respJsonObject(){
        User user = new User();
        user.setUsername("홍길동");

        return user; // 1. MessageConverter가 자동으로 JavaObject를 Json(구:xml)으로 변경해서 통신을 통해 응답을 해준다.
                     // 2. @RestController 일때만 MessageConverter가 작동한다.
                     // 3. 따라서 java object를 반환하면 된다.(MessageConverter가 Java Object를 Json으로 바꿔준다)
    }

}
