package hj.backend.controller;


import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import hj.backend.domain.MemberLogin;
import hj.backend.service.MemberLoginService;
import static hj.backend.service.MemberLoginConst.YES_ID_PWD;

@RequestMapping("login")
@Controller
@AllArgsConstructor
public class MemberLoginController {
    private final MemberLoginService memberLoginService;

    @GetMapping("form.do")
    public String form(){
        return "login/form";
    }

    @PostMapping("login.do")
    public String login(MemberLogin memberLogin, HttpSession session, Model model){
        int result = memberLoginService.check(memberLogin.getEmail(), memberLogin.getPwd());
        if(result == YES_ID_PWD){ //로긴성공 일때
            MemberLogin loginOkUser = memberLoginService.getLogin(memberLogin.getEmail());
            session.setAttribute("loginOkUser", loginOkUser);
        }
        model.addAttribute("result", result);

        return "login/msg";
    }
    @GetMapping("logout.do")
    public String logout(HttpSession session){
        //session.removeAttribute("loginOkUser");
        session.invalidate(); //모든 객체 제거
        return "redirect:../";
    }
}
