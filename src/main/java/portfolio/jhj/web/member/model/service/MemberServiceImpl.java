package portfolio.jhj.web.member.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.jhj.web.member.model.dto.Member;
import portfolio.jhj.web.member.model.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member selectMember() {
        return memberRepository.serlectMember();
    }



}
