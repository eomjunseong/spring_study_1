package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final 붙은 애들 생성자 만들어줌
public class OrderServiceImpl implements  OrderService{
//DIP 위반 : 추상에만 의존하도록 변경(인터페이스에만 의존하도록), but 현재 구현체에도 의존
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();//DIP 위반 OCP 위반
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //DIP 위반 OCP 위반
//    private DiscountPolicy discountPolicy; //이렇게하면 DIP 준수한거임,

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    @Autowired //@RequiredArgsConstructor
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
