package com.sms.service;

import com.sms.entity.Teacher;
import com.sms.entity.User;
import com.sms.exception.EntityNotFoundException;
import com.sms.repository.TeacherRepository;
import com.sms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeacherServiceImpl implements TeacherService {

    private UserRepository userRepository;
    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(UserRepository userRepository, TeacherRepository teacherRepository) {
        this.userRepository = userRepository;
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
    public Teacher save(Teacher teacher) {
        Teacher savedTeacher = teacherRepository.save(new Teacher(UUID.randomUUID().toString(), teacher.getUsername(), teacher.getPassword(), teacher.getYearsOfExperience(), teacher.getDegree()));
        userRepository.save(new User(savedTeacher, teacher.getPassword(), true));

        return savedTeacher;
    }

    @Override
    public Teacher update(Teacher teacher) {
        return null;
    }

    @Override
    public void deleteById(long id) {
        //delete user and mapped teacher
    }

}
