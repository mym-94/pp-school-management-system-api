package com.sms.controller;

import com.sms.entity.Country;
import com.sms.service.CountryService;
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
 * <h1>RESTFUL Country Controller API</h1>
 *
 * @author Mahmoud Yahia
 * @version 1.0.0
 * @since January 2020
 */

@RestController
@RequestMapping(value = "/v1/countries")
@Tag(name = "Country", description = "Countries RESTFUL API")
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    @Operation(summary = "Find all Countries", description = "RESTFUL endpoint returning list of Countries", tags = {"country"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "List of Countries",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Country.class))))})
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @Operation(summary = "Find Country by ID", description = "Returns a single Country", tags = {"Country"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = Country.class))),
            @ApiResponse(responseCode = "404", description = "Country not found")})
    @GetMapping("/{id}")
    public Country findById(@PathVariable("id") long id) {
        return countryService.findById(id);
    }

    @Operation(summary = "Add a new Country", description = "Save/Update a single Country", tags = {"Country"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Country created",
                    content = @Content(schema = @Schema(implementation = Country.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping
    public Country saveOrUpdate(@RequestBody Country country) {
        return countryService.saveOrUpdate(country);
    }

    @Operation(summary = "Deletes a Country", description = "Delete a single Country", tags = { "Country" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Country not found") })
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        countryService.deleteById(id);
    }

}
