package com.cos.photogramstart.domain.subscribe;

import com.cos.photogramstart.domain.user.User;
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
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "subscribe_uk",
                        columnNames = {"fromUserId", "toUserId"} // 실제 db의 컬럼 명을 적기
                )
        }
)
public class Subscribe {
    @Id // primary key로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
    private int id;

    @JoinColumn(name = "fromUserId")
    @ManyToOne
    private User fromUser; // 구독하는 유저

    @JoinColumn(name = "toUserId")
    @ManyToOne
    private User toUser; // 구독받는 유저

    private LocalDateTime createDate; // 데이터가 언제들어왔는지

    @PrePersist // 디비에 insert 되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

}
