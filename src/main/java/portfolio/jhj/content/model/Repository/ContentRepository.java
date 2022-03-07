package portfolio.jhj.content.model.Repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import portfolio.jhj.content.model.dto.Content;

import java.util.List;

@Mapper
public interface ContentRepository {

    @Insert("insert into board(ID,content) values (id_seq.nextval,#{content})")
    void insert(Content content);

    @Select("select * from board order by id desc")
    List<Content> selectAll();

}
