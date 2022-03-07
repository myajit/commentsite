package portfolio.jhj.web.member.model.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import portfolio.jhj.web.member.model.dto.Member;

import java.util.Optional;

@Mapper
public interface MemberRepository {
    
    @Select("select * from member")
    Member serlectMember();

    @Select("select * from member where login_id = #{loginId}")
    Optional<Member> selectMemberByLoginId(String loginId);

}
