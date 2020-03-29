package com.sms.service;

import com.sms.entity.Student;
import com.sms.entity.Subject;
import com.sms.exception.EntityNotFoundException;
import com.sms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(long id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Student .class.getName(), id));
    }

    @Override
    public Student saveOrUpdate(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteById(long id) {
        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException(Subject.class.getName(), id);
        }
    }

}
