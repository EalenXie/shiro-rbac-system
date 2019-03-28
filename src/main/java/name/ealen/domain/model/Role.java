package name.ealen.domain.model;


import javax.persistence.*;
import java.util.List;

/**
 * Created by EalenXie on 2019/3/25 11:18.
 * <p>
 * 角色(Role) 角色下面对应多个权限
 */
@Entity
@Table(name = "system_shiro_role")
public class Role extends BaseEntity {

    @Column(unique = true)
    private String name;                    //角色名 唯一
    private String description;             //描述信息
    @ManyToMany(fetch= FetchType.EAGER)
    private List<Permission> permissions;   //一个用户角色对应多个权限

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
