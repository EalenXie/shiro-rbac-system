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


2. 请为这个例子测试准备基础数据: shiro-rbac-system.sql


#### 3 . 相关测试 : 

1. 用户1，zhangsan登陆(只有user权限)，只能访问相关的api和页面 : 

![](https://img2018.cnblogs.com/blog/994599/201903/994599-20190328091643744-402886749.png)



#### 4. 加密参考 : 
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



访问 : localhost:8082/login
