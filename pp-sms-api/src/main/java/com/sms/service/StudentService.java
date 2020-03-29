package com.sms.service;

import com.sms.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();
    Student findById(long id);
    Student saveOrUpdate(Student student);
    void deleteById(long id);

}
