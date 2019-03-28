package name.ealen;

import name.ealen.domain.dao.PermissionRepository;
import name.ealen.domain.dao.RoleRepository;
import name.ealen.domain.dao.UserRepository;
import name.ealen.domain.model.Permission;
import name.ealen.domain.model.Role;
import name.ealen.domain.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by EalenXie on 2019/3/27 9:36.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InitDataTest {


    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 新增 权限测试
     */
    @Test
    public void addPermissionTest() {

        Permission addPermission = new Permission();
        addPermission.setName("add");
        addPermission.setUrl("/add");
        addPermission.setDescription("新增权限");
        addPermission.setCreateTime(new Date());
        addPermission.setUpdateTime(new Date());

        Permission updatePermission = new Permission();
        updatePermission.setName("update");
        updatePermission.setUrl("/update");
        updatePermission.setDescription("修改权限");
        updatePermission.setCreateTime(new Date());
        updatePermission.setUpdateTime(new Date());

        Permission deletePermission = new Permission();
        deletePermission.setName("delete");
        deletePermission.setUrl("/delete");
        deletePermission.setDescription("删除权限");
        deletePermission.setCreateTime(new Date());
        deletePermission.setUpdateTime(new Date());

        permissionRepository.save(addPermission);
        permissionRepository.save(updatePermission);
        permissionRepository.save(deletePermission);
    }


    /**
     * 新增 角色测试
     */
    @Test
    public void addRoleTest() {

        //管理员 具有所有权限
        List<Permission> permissions = permissionRepository.findAll();
        Role administrator = new Role();
        administrator.setName("administrator");
        administrator.setDescription("管理员权限");
        administrator.setPermissions(permissions);
        administrator.setCreateTime(new Date());
        administrator.setUpdateTime(new Date());
        roleRepository.save(administrator);
        permissions.clear();

        //用户只有部分权限 比如只有新增,修改权限
        Permission addPermission = permissionRepository.findByName("add");
        Permission updatePermission = permissionRepository.findByName("update");
        permissions.add(addPermission);
        permissions.add(updatePermission);
        Role user = new Role();
        user.setName("user");
        user.setDescription("用户权限");
        user.setPermissions(permissions);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        roleRepository.save(user);

    }

    @Test
    public void addUserTest() {
        //ealen 具有管理员角色
        List<Role> roles = new ArrayList<>();
        Role admin = roleRepository.findByName("administrator");
        roles.add(admin);
        User ealen = new User();
        ealen.setUsername("ealenxie");
        ealen.setPasswordSalt("5371f568a45e5ab1f442c38e0932aef24447139b");
        ealen.setPassword("dde5deadfcaa4267804832b063f4f8f9");
        ealen.setCreateTime(new Date());
        ealen.setUpdateTime(new Date());
        ealen.setRoles(roles);
        userRepository.save(ealen);
        roles.clear();

        //zhangsan 具有用户角色
        Role user = roleRepository.findByName("user");
        roles.add(user);
        User zhangsan = new User();
        zhangsan.setUsername("zhangsan");
        zhangsan.setPasswordSalt("5371f568a45e5ab1f442c38e0932aef24447139b");
        zhangsan.setPassword("3b574a9959cd4f8a9a3752d34e0f5f33");
        zhangsan.setCreateTime(new Date());
        zhangsan.setUpdateTime(new Date());
        zhangsan.setRoles(roles);
        userRepository.save(zhangsan);
    }

}
