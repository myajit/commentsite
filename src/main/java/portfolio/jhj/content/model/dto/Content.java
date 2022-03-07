package portfolio.jhj.content.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Content {

    private Long id;
    private String content;
    private Date regDt;
    private String dateParse;
}
