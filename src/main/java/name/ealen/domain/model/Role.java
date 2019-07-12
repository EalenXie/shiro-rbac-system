package name.ealen.domain.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by EalenXie on 2019/3/25 11:18.
 * <p>
 * 角色(Role) 角色下面对应多个权限
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "system_shiro_role")
public class Role extends BaseEntity {

    @Column(unique = true)
    private String name;                    //角色名 唯一
    private String description;             //描述信息
    @ManyToMany(fetch= FetchType.EAGER)
    private List<Permission> permissions;   //一个用户角色对应多个权限
}
