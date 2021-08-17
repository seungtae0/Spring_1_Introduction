package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JapRepository를 extends해서 인터페이스만 만들어도, 자동으로 구현체가 생성되고 등록된다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //컬럼명이 name인 경우, 아래처럼만 해도 내부적으로는 아래 쿼리문을 만들게 된다.
    //JPQL select m from Member m where m.name =?
    @Override
    Optional<Member> findByName(String name);
}
