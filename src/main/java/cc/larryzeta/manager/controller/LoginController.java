package cc.larryzeta.manager.controller;

import cc.larryzeta.manager.api.model.ResultEntity;
import cc.larryzeta.manager.config.JwtConfig;
import cc.larryzeta.manager.dao.UserBaseInfoDAO;
import cc.larryzeta.manager.entity.TUserBaseInfo;
import cc.larryzeta.manager.service.AuthService;
import cc.larryzeta.manager.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserBaseInfoDAO userBaseInfoDAO;

    @ResponseBody
    @PostMapping(value = "toLogin")
    public ResultEntity<String> login(String username, String password) {

        ResultEntity<String> result = new ResultEntity<>();
        Map<String, Object> json = new HashMap<>();

        TUserBaseInfo query = new TUserBaseInfo();
        query.setUserName(username);
        TUserBaseInfo user = userBaseInfoDAO.getTUserBaseInfo(query).get(0);
        if (user == null) {
            json.put("error", "用户不存在");
            result.setData(json.toString());
            return result;
        }

        if (!user.getPasswd().equals(password)) {
            json.put("error", "密码不正确");
            result.setData(json.toString());
            return result;
        }

        String token = JwtUtil.sign(username, user.getEmail(), jwtConfig);
        json.put("token", token);
        result.setData(json.toString());

        return result;
    }

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {

        try {
            authService.login(email, password, map, session);
            return "redirect:/service";
        } catch (Exception e) {
            log.warn("[LoginController-login] Exception", e);
        }

        return "login";

    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        authService.logout(session);
        return "redirect:/login";
    }

}
