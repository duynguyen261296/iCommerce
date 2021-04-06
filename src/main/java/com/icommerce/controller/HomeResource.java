package com.icommerce.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.icommerce.model.Product;
import com.icommerce.model.UserRole;
import com.icommerce.repository.UserRoleRepository;

@RestController
public class HomeResource {
    @Autowired
    UserRoleRepository userRoleRepository;

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }

    @GetMapping("/userrole")
    @ResponseBody
    public List<UserRole> getAllProducts() {
        return userRoleRepository.findAll();
    }
}
