package com.toy.shopbatch.processor;

import com.toy.shopbatch.domain.Member;
import com.toy.shopbatch.domain.Member2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class MemberProcessor implements ItemProcessor<Member, Member2> {

    @Override
    public Member2 process(Member member) throws Exception {
        return Member2.createMember2(member);
    }
}
