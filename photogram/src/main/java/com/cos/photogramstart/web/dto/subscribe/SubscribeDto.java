package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {

    private int id;
    private String username;
    private String profileImageUrl;
    private Integer subscribeState;
    private Integer equalUserState; // state변수는 db에서 던질 때 그냥 int면 오류가 나므로 wrapper 클래스인 Integer이용




}
