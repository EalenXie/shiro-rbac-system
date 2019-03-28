package name.ealen.interfaces.facade;

import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EalenXie on 2019/3/26 16:46.
 * 授权相关API
 */
@RestController
public class AuthorizationApiFacade {


    /**
     * 需要 验证 才能访问的api
     */
    @RequestMapping("/requiresAuthentication")
    @RequiresAuthentication
    public Map<String, String> requiresAuthentication() {
        Map<String, String> result = new HashMap<>();
        result.put("msg", "Require Authentication : 需要认证 测试, 能够访问此接口");
        return result;
    }

    /**
     * 需要 用户 身份才能访问的api
     */
    @RequiresUser
    @RequestMapping("/requiresUser")
    public Map<String, String> requiresUser() {
        Map<String, String> result = new HashMap<>();
        result.put("msg", "Require User : 需要用户 测试, 能够访问此接口");
        return result;
    }

    /**
     * 需要 Guest 身份才能访问的api
     */
    @RequiresGuest
    @RequestMapping("/requiresGuest")
    public Map<String, String> requiresGuest() {
        Map<String, String> result = new HashMap<>();
        result.put("msg", "Require Guest : 需要认证 测试, 能够访问此接口");
        return result;
    }

    /**
     * 需要 administrator 角色才能访问的api
     */
    @RequiresRoles("administrator")
    @RequestMapping("/requiresRoleAdministrator")
    public Map<String, String> requiresRoles() {
        Map<String, String> result = new HashMap<>();
        result.put("msg", "require Roles : 该用户具有 administrator 角色, 能够访问此接口");
        return result;
    }

    /**
     * 需要 add 权限才能访问的api
     */
    @RequiresPermissions("add")
    @RequestMapping("/requiresPermissionsAdd")
    public Map<String, String> requiresPermissionsAdd() {
        Map<String, String> result = new HashMap<>();
        result.put("msg", "require Permissions : 该用户具有 add 权限 , 能够访问此接口");
        return result;
    }

    /**
     * 需要 delete 权限才能访问的api
     */
    @RequiresPermissions("delete")
    @RequestMapping("/requiresPermissionsDelete")
    public Map<String, String> requiresPermissionsDelete() {
        Map<String, String> result = new HashMap<>();
        result.put("msg", "require Permissions : 该用户具有 delete 权限 , 能够访问此接口");
        return result;
    }


    /**
     * 公开接口
     */
    @RequestMapping(value = "/open/api/sayHello", method = RequestMethod.POST)
    public Map<String, String> sayHello() {
        Map<String, String> result = new HashMap<>();
        result.put("msg", "这个是公开接口,谁都可以访问");
        return result;
    }
}
