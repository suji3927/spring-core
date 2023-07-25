package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 관심사 분리
// 애플리케이션의 전체 동작 방식을 구성
// 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스
public class AppConfig {

    // MemberServiceImpl에 MemoryMemberRepository 생성 후 의존관계 주입

    /*
    * 역할에 따른 구현이 잘 보여야 한다.
    * */
    public MemberService memberService() { // MemberService 역할
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() { // OrderService 역할
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private static MemoryMemberRepository memberRepository() { // MemoryMemberRepository 역할
        return new MemoryMemberRepository();
    }

    // 할인 정책 변경 시 구성 역할을 담당하는 AppConfig만 변경하면 됨.
    // 구성 영역은 수시로 변경이 가능하다. 단, 사용 영역은 변경 불가
    public DiscountPolicy discountPolicy() { // DiscountPolicy 역할
        return new RateDiscountPolicy();
    }
}
