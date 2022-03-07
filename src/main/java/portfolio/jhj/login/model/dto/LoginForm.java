package portfolio.jhj.login.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty
    private String loginId = "JHJ";
    @NotEmpty
    private String password = "asd123!@#";

}
