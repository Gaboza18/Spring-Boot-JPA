package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    // @Rollback(false) // @Transactional 으로 인해 기본적으로 rollback을 실행한다. false로 설정 -> 데이터 내용 확인용
    public void 회원가입() throws Exception{

        // given
        Member member = new Member();
        member.setName("seo2");

        // when
        Long saveID = memberService.join(member);
        em.flush(); // DB에 쿼리를 강제 실행

        // then
        assertEquals(member, memberRepository.fineOne(saveID));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{

        // given
        Member member1 = new Member();
        member1.setName("seo");

        Member member2 = new Member();
        member2.setName("seo");

        // when
        memberService.join(member1);
        memberService.join(member2);

        // then
        fail("예외 발생");
    }
}