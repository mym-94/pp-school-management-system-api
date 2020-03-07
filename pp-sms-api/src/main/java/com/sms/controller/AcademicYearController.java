package com.sms.controller;

import com.sms.entity.AcademicYear;
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

/**
 * <h1>RESTFUL AcademicYear Controller API</h1>
 *
 * @author Mahmoud Yahia
 * @version 1.0.0
 * @since January 2020
 */

@RestController
@RequestMapping(value = "/v1/academicyears")
@Tag(name = "AcademicYear", description = "AcademicYears RESTFUL API")
public class AcademicYearController {

    private AcademicYearService academicYearService;

    @Autowired
    public AcademicYearController(AcademicYearService academicYearService) {
        this.academicYearService = academicYearService;
    }

    @GetMapping
    @Operation(summary = "Find all Academic Years", description = "RESTFUL endpoint returning list of Academic Years", tags = {"AcademicYear"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "List of Academic Years",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AcademicYear.class))))})
    public List<AcademicYear> findAll() {
        return AcademicYearService.findAll();
    }

    @Operation(summary = "Find AcademicYear by ID", description = "Returns a single AcademicYear", tags = {"AcademicYear"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = AcademicYear.class))),
            @ApiResponse(responseCode = "404", description = "AcademicYear not found")})
    @GetMapping("/{id}")
    public AcademicYear findById(@PathVariable("id") long id) {
        return AcademicYearService.findById(id);
    }

    @Operation(summary = "Add a new AcademicYear", description = "Save/Update a single AcademicYear", tags = {"AcademicYear"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "AcademicYear created",
                    content = @Content(schema = @Schema(implementation = AcademicYear.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping
    public AcademicYear saveOrUpdate(@RequestBody AcademicYear academicYear) {
        return AcademicYearService.saveOrUpdate(academicYear);
    }

    @Operation(summary = "Deletes an AcademicYear", description = "Delete a single AcademicYear", tags = { "AcademicYear" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "AcademicYear not found") })
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        AcademicYearService.deleteById(id);
    }

}
