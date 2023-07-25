package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // OCP 위반 : 할인 정책 변경에 따른 문제점 : 구현체를 변경해줘야 한다.
    // 구현체: FixDiscountPolicy -> RateDiscountPolicy
    // DIP 위반 : OrderServiceImpl이 DiscountPolicy뿐만 아니라 FixDiscountPolicy와 RateDiscountPolicy에도 의존
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 문제 해결 방법 : 인터페이스에만 의존하도록 코드 변경 -> 그에 따른 문제점 또 발생: NullPointerException 발생
    // 최종 해결 방안 : 누군가가 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성 후 주입해주어야 한다.
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
