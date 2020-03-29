package com.sms.controller;

import com.sms.entity.*;
import com.sms.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) { this.studentService = studentService; }

    @Operation(summary = "Find all students", description = "RESTFUL endpoint returning list of students", tags = {"Student"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "List of Students",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class))))})
    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @Operation(summary = "Find Student by ID", description = "Returns a single Student", tags = {"Student"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "404", description = "Student not found")})
    @GetMapping("/{id}")
    public Student findById(@PathVariable("id") long id) {
        return studentService.findById(id);
    }

    @Operation(summary = "Add a new Student", description = "Update a single Student", tags = {"Student"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created",
                    content = @Content(schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping
    public User save(@RequestBody Student student) {
        return studentService.saveOrUpdate(student);
    }

    @Operation(summary = "update existing Student", description = "Update a single Student", tags = {"Student"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student updated",
                    content = @Content(schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PutMapping
    public User update(@RequestBody Student student) {
        return studentService.saveOrUpdate(student);
    }

    @Operation(summary = "Deletes a Student", description = "Delete a single Student", tags = {"Student"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Student not found") })
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        studentService.deleteById(id);
    }

}
