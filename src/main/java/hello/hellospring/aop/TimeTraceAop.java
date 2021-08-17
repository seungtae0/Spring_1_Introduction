package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component // Bean으로 등록해도 되고(Bean으로 명시하는 것을 추천), Component로 해도 된다.
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // Around로 어디에 적용할 지 명시. 보통 패키지 단위로 함.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint.toString()+ " "+ timeMs + "ms");
        }
    }

}
