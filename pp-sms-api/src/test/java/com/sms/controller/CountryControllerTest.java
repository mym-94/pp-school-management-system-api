package com.sms.controller;

import com.sms.SchoolManagementSystemApplication;
import com.sms.entity.Country;
import com.sms.exception.CountryNotFoundException;
import com.sms.service.CountryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@ContextConfiguration
public class CountryControllerTest {

    private final static String countriesUrl = "/v1/countries";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @Test
    void testFindAll() throws Exception {
        // given
        Date testDateObject = new Date(1583413273);
        Country country = new Country(1, "test", testDateObject);
        List<Country> countries = Arrays.asList(country);
        given(countryService.findAll()).willReturn(countries);

        // when + then
        mockMvc.perform(get(countriesUrl))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'name': 'test', 'updatedAt': \"1970-01-19T07:50:13.273+0000\"}]"));
    }

    @Test
    void testFindByIdResourceExist() throws Exception {
        // given
        Date testDateObject = new Date(1583413273);
        Country country = new Country(1, "test", testDateObject);
        given(countryService.findById(1)).willReturn(country);

        // when + then
        mockMvc.perform(get(countriesUrl + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'name': 'test', 'updatedAt': \"1970-01-19T07:50:13.273+0000\"}"));
    }

    @Test
    void testFindByIdResourceNotExist() throws Exception {
        // given
        given(countryService.findById(1)).willThrow(new CountryNotFoundException(1));

        // when + then
        mockMvc.perform(get(countriesUrl + "/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSaveOrUpdate() throws Exception{
        // given
        Date testDateObject = new Date(1583413273);
        Country country = new Country(1, "test", testDateObject);
        given(countryService.saveOrUpdate(any(Country.class))).willReturn(country);

        // when + then
        mockMvc.perform(post(countriesUrl)
                .content("{\"id\": 11,\"email\": \"testagain@again.again\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'name': 'test', 'updatedAt': \"1970-01-19T07:50:13.273+0000\"}"));
    }

    @Test
    void testDeleteByIdResourceExist() throws Exception {
        //when + then
        mockMvc.perform(delete(countriesUrl + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(countryService).deleteById((long) 1);
    }

    @Test
    void testDeleteByIdResourceNotExist() throws Exception {
        // given
        doThrow(new CountryNotFoundException(1)).when(countryService).deleteById(1);

        // when + then
        mockMvc.perform(delete(countriesUrl + "/1"))
                .andExpect(status().isNotFound());
    }

}
