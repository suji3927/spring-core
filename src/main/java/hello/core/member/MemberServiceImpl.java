package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // DIP 위반 : 추상화된 것은 구체적인 것에 의존하면 안된다.
    // MemberServiceImpl은 MemberRepository(추상화)와 MemoryMemberRepository(구체화) 의존
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
