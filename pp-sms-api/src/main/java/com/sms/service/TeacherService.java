package com.sms.service;

import com.sms.entity.Teacher;
import java.util.List;

public interface TeacherService {

    List<Teacher> findAll();
    Teacher findById(long id);
    Teacher save(Teacher teacher);
    Teacher update(Teacher teacher);
    void deleteById(long id);

}
