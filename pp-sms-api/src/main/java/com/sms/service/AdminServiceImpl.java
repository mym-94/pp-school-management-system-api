package com.sms.service;

import com.sms.entity.Admin;
import com.sms.entity.User;
import com.sms.exception.EntityNotFoundException;
import com.sms.repository.AdminRepository;
import com.sms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin findById(long id) {
        return adminRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Admin .class.getName(), id));
    }

    @Override
    public User saveOrUpdate(Admin admin) {
        return adminRepository.save(admin);
//        return adminRepository.save(new Admin(UUID.randomUUID().toString(), admin.getUsername(), admin.getPassword(), admin.getTitle(), true));
    }

    @Override
    public void deleteById(long id) {
        adminRepository.deleteById(id);
    }

}
