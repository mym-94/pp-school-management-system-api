package com.sms.controller;

import com.sms.entity.Admin;
import com.sms.entity.User;
import com.sms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/admins")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) { this.adminService = adminService; }

    @GetMapping("/{id}")
    public Admin findById(@PathVariable("id") long id) {
        return adminService.findById(id);
    }

    @PostMapping
    public User save(@RequestBody Admin admin) {
        return adminService.saveOrUpdate(admin);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        adminService.deleteById(id);
    }

}
