package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() throws Exception {
        //given
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        //when & then
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() throws Exception {
        //given
        MemberService memberService = ac.getBean( MemberService.class);
        //when & then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() throws Exception {
        //given
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        //when & then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX() throws Exception {
        // org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'xxxxx' available
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberServiceImpl.class));
        // public static <T extends Throwable> T assertThrows(Class<T> expectedType, Executable executable)
        // executable 로직이 실행 되면 , expectedType 에러가 발생 해야 한다.
    }

}
