package com.user.dao;

import com.user.entity.Role;
import com.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleDao {
    @Autowired
    private RoleRepository roleRepository;

    public Role fetchRoleByLevel(String level){
        Role roles = roleRepository.findByLevel(level);
        System.out.println("-----------------------------------------------------");
        System.out.println(roles);
        return roles;
    }

    public Role add(Role role){
        return roleRepository.save(role);
    }
}
