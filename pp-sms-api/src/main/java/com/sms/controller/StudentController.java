package com.sms.controller;

import com.sms.entity.*;
import com.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) { this.studentService = studentService; }

    @GetMapping("/{id}")
    public Student findById(@PathVariable("id") long id) {
        return studentService.findById(id);
    }

    @PostMapping
    public User save(@RequestBody Student student) {
        return studentService.saveOrUpdate(student);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        studentService.deleteById(id);
    }

}
