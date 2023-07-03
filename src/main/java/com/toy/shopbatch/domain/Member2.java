package com.toy.shopbatch.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member2 extends BaseDomain {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    public static Member2 createMember2(Member member) {
        Member2 member2 = new Member2();

        member2.name = member.getName();
        member2.address = member.getAddress();

        return member2;
    }
}
