package portfolio.jhj.login.model.service;

import portfolio.jhj.web.member.model.dto.Member;

public interface LoginService {

    Member login(String loginId, String password);

}
