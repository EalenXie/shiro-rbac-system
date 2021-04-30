SpringBoot整合Shiro实现基于角色的权限访问控制(RBAC)系统简单设计从零搭建
=================

#### 1 . 这是基于SpringBoot和Shiro实现的一个角色权限访问控制(RBAC)的系统。

基本描述 : 
    
    1 . 一个用户具有一个角色，或者多个角色
    2 . 一个角色具有一个权限，或者多个权限
    3 . 权限可以访问对应的api，或者url资源等等。

基本实现 : 

    1 . 用户登入登出
    2 . 基于权限控制访问api或者url资源。

#### 2 . 在你运行该应用之前 :

1. 请修改你自己的数据库配置: /application.yml


2. 请为这个例子测试准备基础数据: [shiro-rbac-system.sql](https://github.com/EalenXie/shiro-rbac-system/blob/master/src/main/resources/shiro-rbac-system.sql)


#### 3 . 相关测试 : 

##### 1. 用户zhangsan(只有user权限)，只能访问相关的api和页面 : 
1. zhangsan进行登陆 :

![](https://img2018.cnblogs.com/blog/994599/201903/994599-20190328091643744-402886749.png)
    
2. 登陆成功，跳转到首页。

![](https://img2018.cnblogs.com/blog/994599/201903/994599-20190328091730165-1852880897.png)

3. zhangsan有进入add页面的权限。没有delete页面权限。访问 ``/add`` 则跳转到 新增页

![](https://img2018.cnblogs.com/blog/994599/201903/994599-20190328091843204-795382299.png)

访问 ``/delete``，因为没有权限会跳转到未授权页面。

![](https://img2018.cnblogs.com/blog/994599/201903/994599-20190328091934370-753707810.png)

4. zhangsan只能调用自己拥有角色和权限的api :

![](https://img2018.cnblogs.com/blog/994599/201903/994599-20190328094536849-2112103972.png)

![](https://img2018.cnblogs.com/blog/994599/201903/994599-20190328094635000-655674662.png)

没有相关角色和权限的api不能调用 : 
![](https://img2018.cnblogs.com/blog/994599/201903/994599-20190328095220095-1501717577.png)

#### 5. 代码参考部分 : 

1 . 加密参考 : 
```java
package name.ealen;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

/**
 * Created by EalenXie on 2019/3/25 18:56.
 */
public class ShiroTest {

    private Object encrypt(String username, String enablePassword) {

        String hashAlgorithmName = "md5";//加密算法

        String passwordSalt = "5371f568a45e5ab1f442c38e0932aef24447139b";//密钥

        String salt = passwordSalt + username + passwordSalt; //盐值

        int hashIterations = 1024; //散列次数

        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);//盐

        return new SimpleHash(hashAlgorithmName, enablePassword, credentialsSalt, hashIterations);
    }
    /**
     * 加密测试
     */
    @Test
    public void encryption() {

        Object securePassword = encrypt("ealenxie", "admin");

        System.out.println("ealen的加密后密码 : " + securePassword);

        Object zhangsanPassword = encrypt("zhangsan", "12345");

        System.out.println("zhangsan的加密后密码 : " +zhangsanPassword);

        Object lisiPassword = encrypt("lisi", "12345");

        System.out.println("lisi的加密后密码 : " +lisiPassword);
    }
}
```
2 . 数据库中权限，角色，url过滤器链核心配置参考 ，分别在`UserAuthRealm`和`ShiroConfig`中: 
```java

   /**
     * 权限核心配置 根据数据库中的该用户 角色 和 权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        for (Role role : user.getRoles()) {                                 //获取 角色
            authorizationInfo.addRole(role.getName());                      //添加 角色
            for (Permission permission : role.getPermissions()) {           //获取 权限
                authorizationInfo.addStringPermission(permission.getName());//添加 权限
            }
        }
        return authorizationInfo;
    }
```
```java

    /**
     * 配置 拦截过滤器链.  map的键 : 资源地址 ;  map的值 : 所有默认Shiro过滤器实例名
     * 默认Shiro过滤器实例 参考 : {@link org.apache.shiro.web.filter.mgt.DefaultFilter}
     */
    private Map<String, String> setFilterChainDefinitionMap() {
        Map<String, String> filterMap = new LinkedHashMap<>();
        //注册 数据库中所有的权限 及其对应url
        List<Permission> allPermission = permissionRepository.findAll();//数据库中查询所有权限
        for (Permission p : allPermission) {
            filterMap.put(p.getUrl(), "perms[" + p.getName() + "]");    //拦截器中注册所有的权限
        }
        filterMap.put("/static/**", "anon");    //公开访问的资源
        filterMap.put("/open/api/**", "anon");  //公开接口地址
        filterMap.put("/logout", "logout");     //配置登出页,shiro已经帮我们实现了跳转
        filterMap.put("/**", "authc");          //所有资源都需要经过验证
        return filterMap;
    }
```

3 . 代码结构参考 : [https://github.com/EalenXie/springcloud-microservice-ddd](https://github.com/EalenXie/springcloud-microservice-ddd)

4 . 博客链接 : [https://www.cnblogs.com/ealenxie/p/10610741.html](https://www.cnblogs.com/ealenxie/p/10610741.html)
