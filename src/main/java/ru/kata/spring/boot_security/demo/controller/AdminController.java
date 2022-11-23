package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getUser(Model model, Principal principal, @AuthenticationPrincipal User user) {
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleService.listRoles());
        User userN = userService.showByEmail(principal.getName());
        model.addAttribute("logUser", userN);
        return "admin";
    }

    @PostMapping(value = "/new")
    public String create(@ModelAttribute("user") User user, @RequestParam(value = "roles", required = false) Long roles) {
        user.setRoles(roleService.findRoleById(roles));
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id, @RequestParam(value = "roles", required = false) Long roles) {
        user.setRoles(roleService.findRoleById(roles));
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
