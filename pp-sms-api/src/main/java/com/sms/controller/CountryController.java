package com.sms.controller;

import com.sms.entity.Country;
import com.sms.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h1>RESTFUL Country Controller API</h1>
 *
 * @author Mahmoud Yahia
 * @since January 2020
 * @version 1.0.0
 */

@RestController
@RequestMapping(value = "/v1/countries")
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public Country findById(@PathVariable("id") long id) {
        return countryService.findById(id);
    }

    @PostMapping
    public Country saveOrUpdate(@RequestBody Country country) {
        return countryService.saveOrUpdate(country);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        countryService.deleteById(id);
    }

}
