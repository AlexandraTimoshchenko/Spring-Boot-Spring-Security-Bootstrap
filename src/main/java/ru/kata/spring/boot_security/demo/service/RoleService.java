package ru.kata.spring.boot_security.demo.service;



import org.springframework.security.core.GrantedAuthority;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> listRoles();
    List<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles);
    List<Role> findRoleByName(String name);
    List<Role> findRoleById(Long id);
}
