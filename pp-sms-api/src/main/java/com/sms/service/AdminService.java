package com.sms.service;

import com.sms.entity.Admin;
import com.sms.entity.User;

import java.util.List;

public interface AdminService {

    List<Admin> findAll();
    Admin findById(long id);
    User saveOrUpdate(Admin admin);
    void deleteById(long id);

}
