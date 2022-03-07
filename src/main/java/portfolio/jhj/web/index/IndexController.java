package portfolio.jhj.web.index;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import portfolio.jhj.content.model.Repository.ContentRepository;
import portfolio.jhj.content.model.dto.Content;
import portfolio.jhj.login.model.dto.LoginForm;
import portfolio.jhj.login.model.service.LoginServiceImpl;
import portfolio.jhj.web.SessionConst;
import portfolio.jhj.web.member.model.dto.Member;
import portfolio.jhj.web.member.model.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final MemberService memberService;
    private final LoginServiceImpl loginService;
    private final ContentRepository contentRepository;

    @GetMapping("/")
    public String index(@ModelAttribute("loginForm") LoginForm form) {
        return "login/index";
    }



    @PostMapping("/")
    public String main(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
                       @RequestParam(defaultValue = "/") String redirectURL,
                       HttpServletRequest request) {

        if(bindingResult.hasErrors()) {
            return "login/index";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null) {
            bindingResult.reject("loginFail","아이디: JHJ 비밀번호: asd123!@#");
            return "login/index";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:main";
    }

    @GetMapping("main")
    public String main(@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member loginMember, Model model) {

        if(loginMember == null) {
            return "/";
        }

        model.addAttribute("member", loginMember);
        return "main/main";
    }

    @CrossOrigin("*")
    @PostMapping("content")
    @ResponseBody
    public String  save(@RequestParam Map<String, Object> param, Model model,
                        HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin","*");

        Content saveContent = new Content();
        saveContent.setContent(param.get("content").toString());

        contentRepository.insert(saveContent);
        List<Content> contents = contentRepository.selectAll();
        model.addAttribute("contents",contents);

        return "success";
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("getcontent")
    public String responseBodyJason(HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Origin","*");

        List<Content> contents = contentRepository.selectAll();

        for (Content content : contents) {
            Date regDt = content.getRegDt();
            SimpleDateFormat dt = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
            dt.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            String dateFormat = dt.format(regDt);
            content.setDateParse(dateFormat);
        }

        String json = new Gson().toJson(contents);
        log.info("data={}" , json.toString());
        return json;
    }



    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
