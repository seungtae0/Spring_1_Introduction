package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // @Controller, @Service, @Repository는 컴포넌트 스캔 방식이다. + 자동 의존관계 설정은 @Autowired
// @Component 가 있으면 -> 스프링 컨테이너에 스프링 빈으로 자동 등록된다.
// hello.hellospring 패키지 안에서만 컴포넌트 스캔이 이루어진다.
public class MemberController {

    private  final MemberService memberService;

    @Autowired // 디펜던시 인젝션. 의존성 주입.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMembersForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
