package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    // 정적 컨텐츠
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";

    }

    // MVC와 템플릿 엔진
    // @RequestParam("name3") --> localhost:8080/hello-mvc?name3=안녕하세요
    // --> 그러면 String name 선언한 name에 그 값이 들어간다.
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name3") String name, Model model) {
        model.addAttribute("name2", name); // hello-template.html에서 사용하는 ${name2}
        return "hello-template";
    }

    // API 문자 반환
    @GetMapping("hello-string")
    @ResponseBody
    // --> @ResponseBody 를 사용하면 뷰 리졸버를 사용하지 않고, HttpMessageConverter가 동작
    // 기본 문자처리 : StringHttpMessageConverter
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // ★ API 객체 반환 (Json 방식)
    @GetMapping("hello-api")
    @ResponseBody
    // 기본 객체처리 : MappingJackson2HttpMessageConverter
    public Hello helloApi(@RequestParam("name") String name2) {
        Hello hello = new Hello(); // Ctrl + Shift + Enter : 소스 자동완성 기능
        hello.setName(name2);

        return hello;
    }

    static class Hello {
        private String name;

        // Alt + Insert : Get/Set 자동생성 가능
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
