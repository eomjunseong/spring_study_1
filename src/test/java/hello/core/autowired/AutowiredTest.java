package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{
        @Autowired(required = false) //의존 관계없으면 호출 자체가 안됨 --> Member빈없어서
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }
        @Autowired//호출은 되지만 널로 들어감
        public void setNoBean2(@Nullable Member member){
            System.out.println("noBean2 = " + member);
        }
        @Autowired(required = false) // 호출은 되지만 없으면 Optional.empty
        public void setNoBean3(Optional<Member> member){
            System.out.println("noBean3 = " + member);
        }
    }
}
