package name.ealen;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Assert;
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
        Assert.assertEquals("dde5deadfcaa4267804832b063f4f8f9", securePassword.toString());
        Object zhangsanPassword = encrypt("zhangsan", "12345");
        System.out.println("zhangsan的加密后密码 : " + zhangsanPassword);
        Assert.assertEquals("3b574a9959cd4f8a9a3752d34e0f5f33", zhangsanPassword.toString());
        Object lisiPassword = encrypt("3d1a32c2a11641b5551f1cbfc0a007e3", "12345");
        System.out.println("lisi的加密后密码 : " + lisiPassword.toString());
    }


}
