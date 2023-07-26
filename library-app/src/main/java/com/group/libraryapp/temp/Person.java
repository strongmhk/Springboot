package com.group.libraryapp.temp;

import javax.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    private String name;

    @OneToOne
    private Address address;

    public void setAddress(Address address) {
        this.address = address;
        this.address.setPerson(this); // 여기서 this는 person 인스턴스, 이 코드를 통해 트랜 잭션 범위의 안에서 address의 person도 세팅해준다
    }
}
