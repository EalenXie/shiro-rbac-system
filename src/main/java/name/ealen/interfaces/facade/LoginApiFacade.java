package name.ealen.interfaces.facade;

import name.ealen.domain.service.UserLoginApiService;
import name.ealen.domain.vo.Resp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "/open/api/login")
    public ResponseEntity<Resp> login(@RequestBody Map<String, String> params) {
        if (params.containsKey("username") && params.containsKey("password")) {
            String username = params.get("username");
            String password = params.get("password");
            return userLoginApiService.login(username, password);
        } else {
            return ResponseEntity.badRequest().body(new Resp("缺少重要参数或参数无效"));

        }
    }

    /**
     * 登出 API
     */
    @PostMapping(value = "/open/api/logout")
    public ResponseEntity<Resp> logout() {
        return userLoginApiService.logout();
    }

}
