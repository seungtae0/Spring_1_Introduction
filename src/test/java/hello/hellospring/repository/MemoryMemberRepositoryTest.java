package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


//class 단에서도 Test 실행 가능
public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository;

    public MemoryMemberRepositoryTest() {
        repository = new MemoryMemberRepository();
    }

    //Test는 순서대로 실행되지 않음
    //Test할 때 마다 AfterEach가 실행됨. clear를 해주어야 함.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        //1. print (실무 시, 모두 찍어서 볼 수 없으므로 3번으로 진행)
        //System.out.println("result = " + (result == member));

        //2.
        //assertEquals(member, result); // (기대값, 실제값) 값이 다르면 정상 실행 되지 않음

        //3.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //복사 후, Shift + F6 하면 이름 한번에 변경 가능
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
