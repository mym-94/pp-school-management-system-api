package com.sms.controller;

import com.sms.entity.*;
import com.sms.service.TeacherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/teachers")
public class TeacherController {

    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) { this.teacherService = teacherService; }

    @GetMapping("/{id}")
    public Teacher findById(@PathVariable("id") long id) {
        return teacherService.findById(id);
    }

    @PostMapping
    public User save(@RequestBody Teacher teacher) {
        return teacherService.saveOrUpdate(teacher);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        teacherService.deleteById(id);
    }

}
