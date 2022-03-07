package portfolio.jhj.login.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.jhj.web.member.model.dto.Member;
import portfolio.jhj.web.member.model.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

        private final MemberRepository memberRepository;

        @Override
        public Member login(String loginId, String password) {
                return memberRepository.selectMemberByLoginId(loginId)
                        .filter(m -> m.getPassword().equals(password))
                        .orElse(null);
        }
}
