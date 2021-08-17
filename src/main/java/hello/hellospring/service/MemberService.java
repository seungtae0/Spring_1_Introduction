package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Test를 위해서 직접 Test 폴더에 만들지 않아도 된다
// Ctrl + Shift + T 해서 TEST 만들 수 있다.
//@Service // @Service를 해주어야 컨테이너에 등록되어, 컨트롤러에서 불러서 사용할 수 있다.
//--> 자바 코드로 직접 스프링 빈 등록하기. @Service, @Repository, @Autowired 애노테이션을 제거하고 진행
//--> @Configuration을 달아준 SpringConfig에서 @Bean을 달아서 작성해준다.
@Transactional
public class MemberService {
    //private final MemberRepository memberRepository = new MemoryMemberRepository(); // 이렇게 쓰면 테스트 할 때 new로 또 만들기 때문에, 서로 다른 repository가 된다. 그래서 아래처럼 직접 받아서 저장하는 것으로 수정함.
    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    * 회원가입
    */
    public Long join(Member member) {
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        //같은 이름이 있는 중복 회원X
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
         */
            // Ctrl + Shift + Alt + T : 리팩토링 관련 단축키
            // Extract Method 해서 메써드로 따로 자동생성되게 뽑을 수 있음
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();


    }

    //같은 이름이 있는 중복 회원X
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /*
    * 전체 회원 조회
     */
    public List<Member> findMembers() {
            return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }




}
