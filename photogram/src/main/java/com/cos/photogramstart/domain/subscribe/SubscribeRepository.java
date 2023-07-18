package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    // 메서드 이름 맨 앞의 m은 내가 직접만들었다는 약어
    @Modifying // INSERT, DELETE, UPDATE를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요
    @Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId,now())",nativeQuery = true) // :fromUserId, 파라미터로 받은 fromUserId 값을 넣어준다는 뜻
    void mSubscribe(int fromUserId, int toUserId);

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND :toUserId",nativeQuery = true)
    void mUnSubscribe(int fromUserId, int toUserId);

}
