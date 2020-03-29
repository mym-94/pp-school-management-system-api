package com.sms.service;

import com.sms.entity.Teacher;
import com.sms.exception.EntityNotFoundException;
import com.sms.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Teacher .class.getName(), id));
    }

    @Override
    public Teacher saveOrUpdate(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteById(long id) {
        teacherRepository.deleteById(id);
    }

}
