package com.sms.service;

import com.sms.entity.Teacher;
import com.sms.entity.User;
import java.util.List;

public interface TeacherService {

    List<Teacher> findAll();
    Teacher findById(long id);
    Teacher saveOrUpdate(Teacher teacher);
    void deleteById(long id);

}
