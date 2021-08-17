package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Service, Repository에서 @붙여서 컴포넌트 지정한 걸 지우고 아래 처럼도 가능 (컨트롤러는 그대로 둬야함)
//자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {

    /*
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    */

    //--> JPA방식으로 하기위해서 EntityManager로 작성하겠다.
    /*
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    */

    //--> 스프링 데이터 JPA 방식으로 하기위해서 아래처럼 작성하겠다.
    private final MemberRepository memberRepository;

    // Jpa를 extends한 인터페이스만 만들어도, 구현체가 만들어지고 빈에 자동 등록되어서 아래에 DI가 가능하다.
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService() {
        //return new MemberService(memberRepository());
        return new MemberService(memberRepository); // 스프링 데이터 JPA 방식할 때
    }

    /*
    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        // return new JpaMemberRepository(em);
    }
    */
}
