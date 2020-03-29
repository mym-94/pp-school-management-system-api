package com.sms.controller;

import com.sms.entity.Admin;
import com.sms.entity.User;
import com.sms.service.AdminService;
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
@RequestMapping(value = "/v1/admins")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) { this.adminService = adminService; }

    @Operation(summary = "Find all admins", description = "RESTFUL endpoint returning list of admins", tags = {"Admin"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "List of Admins",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Admin.class))))})
    @GetMapping
    public List<Admin> findAll() {
        return adminService.findAll();
    }

    @Operation(summary = "Find Admin by ID", description = "Returns a single Admin", tags = {"Admin"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = Admin.class))),
            @ApiResponse(responseCode = "404", description = "Admin not found")})
    @GetMapping("/{id}")
    public Admin findById(@PathVariable("id") long id) {
        return adminService.findById(id);
    }

    @Operation(summary = "Add a new Admin", description = "Update a single Admin", tags = {"Admin"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Admin created",
                    content = @Content(schema = @Schema(implementation = Admin.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping
    public User save(@RequestBody Admin admin) {
        return adminService.saveOrUpdate(admin);
    }

    @Operation(summary = "update existing Admin", description = "Update a single Admin", tags = {"Admin"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Admin updated",
                    content = @Content(schema = @Schema(implementation = Admin.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PutMapping
    public User update(@RequestBody Admin admin) {
        return adminService.saveOrUpdate(admin);
    }

    @Operation(summary = "Deletes a Admin", description = "Delete a single Admin", tags = {"Admin"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Admin not found") })
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        adminService.deleteById(id);
    }

}
