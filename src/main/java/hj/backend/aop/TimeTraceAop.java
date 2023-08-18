package hj.backend.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component //자동으로 스프링빈 등록
@Aspect
public class TimeTraceAop {
    @Around("execution(* hj.backend..*(..))") //backend 하위에 있는 모든 메소드에 건다
   // @Around("execution(* hj.backend.service..*(..))") //Only service 하위에 있는 모든 메소드에 건다( *() ) //서비스 메소드
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("STRAT: "+start);
        try{
            Object result = joinPoint.proceed();
            return result;
        }finally{
            long end = System.currentTimeMillis();
            long timeMs = end - start;
            System.out.println("##걸린시간: "+joinPoint.toString()+" "+timeMs+"ms");
        }
    }
}
