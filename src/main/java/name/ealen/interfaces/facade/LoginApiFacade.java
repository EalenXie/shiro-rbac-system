package name.ealen.interfaces.facade;

import name.ealen.domain.service.UserLoginApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by EalenXie on 2019/3/25 18:50.
 * 前后分离模式 登陆 API
 */
@RestController
public class LoginApiFacade {

    @Resource
    private UserLoginApiService userLoginApiService;

    /**
     * 登陆 API
     *
     * @param params 包含必传信息 用户名 密码
     */
    @RequestMapping(value = "/open/api/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody Map<String, String> params) {
        if (params.containsKey("username") && params.containsKey("password")) {
            String username = params.get("username");
            String password = params.get("password");
            return userLoginApiService.login(username, password);
        } else {
            return ResponseEntity.badRequest().body("{\"message\":\"缺少重要参数或参数无效\"}");
        }
    }

    /**
     * 登出 API
     */
    @RequestMapping(value = "/open/api/logout", method = RequestMethod.POST)
    public ResponseEntity logout() {
        return userLoginApiService.logout();
    }

}
