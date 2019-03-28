package name.ealen.interfaces.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by EalenXie on 2019/3/26 16:03.
 */
@Controller
public class LoginController {


    /**
     * 登陆页
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 退出 后跳转登陆页
     */
    @RequestMapping("/logout")
    public String logout() {
        return "login";
    }

    /**
     * 首页
     */
    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    /**
     * 未授权页
     */
    @RequestMapping({"/unauthorized"})
    public String unauthorized() {
        return "unauthorized";
    }

}
