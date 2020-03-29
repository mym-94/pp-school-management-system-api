package com.sms.controller;

import com.sms.entity.*;
import com.sms.service.TeacherService;
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
@RequestMapping(value = "/v1/teachers")
public class TeacherController {

    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) { this.teacherService = teacherService; }

    @Operation(summary = "Find all teachers", description = "RESTFUL endpoint returning list of teachers", tags = {"Teacher"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "List of Teachers",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Teacher.class))))})
    @GetMapping
    public List<Teacher> findAll() {
        return teacherService.findAll();
    }

    @Operation(summary = "Find Teacher by ID", description = "Returns a single Teacher", tags = {"Teacher"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = Teacher.class))),
            @ApiResponse(responseCode = "404", description = "Teacher not found")})
    @GetMapping("/{id}")
    public Teacher findById(@PathVariable("id") long id) {
        return teacherService.findById(id);
    }

    @Operation(summary = "Add a new Teacher", description = "Update a single Teacher", tags = {"Teacher"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Teacher created",
                    content = @Content(schema = @Schema(implementation = Teacher.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping
    public User save(@RequestBody Teacher teacher) {
        return teacherService.saveOrUpdate(teacher);
    }

    @Operation(summary = "update existing Teacher", description = "Update a single Teacher", tags = {"Teacher"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Teacher updated",
                    content = @Content(schema = @Schema(implementation = Teacher.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PutMapping
    public Teacher update(@RequestBody Teacher teacher) { return teacherService.saveOrUpdate(teacher); }

    @Operation(summary = "Deletes a Teacher", description = "Delete a single Teacher", tags = {"Teacher"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Teacher not found") })
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        teacherService.deleteById(id);
    }

}
