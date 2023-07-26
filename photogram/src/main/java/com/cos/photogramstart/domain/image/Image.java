package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // 디비에 테이블을 생성
public class Image { // 이미지 -> 유저 = 1 : 1, 유저 -> 이미지 = 1 : N
    @Id // primary key로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
    private int id;

    // 오브젝트를 콘솔에 출력할 때 문제가 될 수 있어서 User 부분을 출력되지 않게 함.
    /*@Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", postImageUrl='" + postImageUrl + '\'' +
                ", createDate=" + createDate +
                '}';
    }*/

    private String caption; // 사진 설명
    private String postImageUrl; // 사진을 전송받아서 그 사진을 서버에 특정 폴더에 저장 - DB에 그 저장된 경로를 insert(

    @JsonIgnoreProperties({"images"})
    @JoinColumn(name = "userId") // DB에 객체를 저장하면 FK로 저장되기 때문에 FK의 이름 정해줌
    @ManyToOne
    private User user; // 누가 업로드 했는지

    // 이미지 좋아요

    // 댓글

    private LocalDateTime createDate; // 데이터가 언제들어왔는지

    @PrePersist // 디비에 insert 되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

}

