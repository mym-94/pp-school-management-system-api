package com.sms.controller;

import com.sms.entity.AcademicYear;
import com.sms.entity.Country;
import com.sms.service.AcademicYearService;
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
@RequestMapping(value = "/v1/academicyears")
@Tag(name = "Academic Year", description = "Academic Years RESTFUL API")
public class AcademicYearController {

    private AcademicYearService academicYearService;

    @Autowired
    public AcademicYearController(AcademicYearService academicYearService) { this.academicYearService = academicYearService; }

    @GetMapping
    @Operation(summary = "Find all Academic Years", description = "RESTFUL endpoint returning list of academic Years", tags = {"AcademicYear"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "List of Academic Years",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Country.class))))})
    public List<AcademicYear> findAll() {
        return academicYearService.findAll();
    }

    @Operation(summary = "Find Academic Year by ID", description = "Returns a single Academic year", tags = {"AcademicYear"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = AcademicYear.class))),
            @ApiResponse(responseCode = "404", description = "Country not found")})
    @GetMapping("/{id}")
    public AcademicYear findById(@PathVariable("id") long id) {
        return academicYearService.findById(id);
    }

    @Operation(summary = "Add a new Academic Year", description = "Save/Update a single Academic Year", tags = {"AcademicYear"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Academic Year created",
                    content = @Content(schema = @Schema(implementation = AcademicYear.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping
    public AcademicYear saveOrUpdate(@RequestBody AcademicYear academicYear) {
        return academicYearService.saveOrUpdate(academicYear);
    }

    @Operation(summary = "Deletes an Academic Year", description = "Delete a single Academic Year", tags = { "AcademicYear" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Academic Year not found") })
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        academicYearService.deleteById(id);
    }

}
