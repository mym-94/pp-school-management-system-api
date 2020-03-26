package com.sms.controller;

import com.sms.entity.Subject;
import com.sms.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/subjects")
@Tag(name = "Subject", description = "Subjects RESTFUL API")
public class SubjectController {

    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    @Operation(summary = "Find all subjects", description = "RESTFUL endpoint returning list of Subjects", tags = {"Subject"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "List of Subjects",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Subject.class))))})
    public List<Subject> findAll() {
        return subjectService.findAll();
    }

    @Operation(summary = "Find Subject by ID", description = "Returns a single Subject", tags = {"Subject"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = Subject.class))),
            @ApiResponse(responseCode = "404", description = "Subject not found")})
    @GetMapping("/{id}")
    public Subject findById(@PathVariable("id") long id) {
        return subjectService.findById(id);
    }

    @Operation(summary = "Add a new Subject", description = "Save/Update a single Subject", tags = {"Subject"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subject created",
                    content = @Content(schema = @Schema(implementation = Subject.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping
    public Subject saveOrUpdate(@RequestBody Subject subject) {
        return subjectService.saveOrUpdate(subject);
    }

    @Operation(summary = "Deletes a Subject", description = "Delete a single Subject", tags = { "Subject" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Subject not found") })
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        subjectService.deleteById(id);
    }

}
