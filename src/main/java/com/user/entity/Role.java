package com.user.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="level")
    private String level;

    @Column(name="permissions")
    private String[] permissions;

    public Role(){}

    public Role(String level, String[] permissions){
        this.level = level;
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public String[] getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", level='" + level + '\'' +
                ", permissions=" + Arrays.toString(permissions) +
                '}';
    }
}
