package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em; // 의존성 주입

    // 저장기능 JPA
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    // 단건조회
    public Member fineOne(Long id){
        return em.find(Member.class, id);
    }

    // 리스트 형식 조회
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); // JPQL
    }

    // 이름으로 조회
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
