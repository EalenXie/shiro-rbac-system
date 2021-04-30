package name.ealen.domain.service;

import name.ealen.application.RBACService;
import name.ealen.domain.vo.Resp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by EalenXie on 2019/3/26 16:31.
 */
@Service
public class UserLoginApiService implements RBACService {


    @Override
    public ResponseEntity<Resp> login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);// 执行认证登陆
        return ResponseEntity.ok(new Resp("登录成功"));
    }

    @Override
    public ResponseEntity<Resp> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResponseEntity.ok(new Resp("登出成功"));
    }

}
