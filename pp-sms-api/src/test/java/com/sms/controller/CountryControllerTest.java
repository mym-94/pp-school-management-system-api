package com.sms.controller;

import com.sms.entity.Country;
import com.sms.service.CountryService;
import com.sms.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllerTest {

    private static final String COUNTRIES_URL = "/v1/countries";

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
        mockMvc.perform(get(COUNTRIES_URL))
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
        mockMvc.perform(get(COUNTRIES_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'name': 'test', 'updatedAt': \"1970-01-19T07:50:13.273+0000\"}"));
    }

    @Test
    void testFindByIdResourceNotExist() throws Exception {
        // given
        given(countryService.findById(1)).willThrow(new EntityNotFoundException(Country.class.getName(), 1));

        // when + then
        mockMvc.perform(get(COUNTRIES_URL + "/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSave() throws Exception{
        // given
        Date testDateObject = new Date(1583413273);
        Country country = new Country(1, "test", testDateObject);
        given(countryService.saveOrUpdate(any(Country.class))).willReturn(country);

        // when + then
        mockMvc.perform(post(COUNTRIES_URL)
                .content("{\"name\": \"test\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'name': 'test', 'updatedAt': \"1970-01-19T07:50:13.273+0000\"}"));
    }

    @Test
    void testUpdate() throws Exception{
        // given
        Date testDateObject = new Date(1583413273);
        Country country = new Country(1, "test", testDateObject);
        given(countryService.saveOrUpdate(any(Country.class))).willReturn(country);

        // when + then
        mockMvc.perform(put(COUNTRIES_URL)
                .content("{\"id\": 1,\"name\": \"test\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'name': 'test', 'updatedAt': \"1970-01-19T07:50:13.273+0000\"}"));
    }

    @Test
    void testDeleteByIdResourceExist() throws Exception {
        //when + then
        mockMvc.perform(delete(COUNTRIES_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(countryService).deleteById(1);
    }

    @Test
    void testDeleteByIdResourceNotExist() throws Exception {
        // given
        doThrow(new EntityNotFoundException(Country.class.getName(), 1)).when(countryService).deleteById(1);

        // when + then
        mockMvc.perform(delete(COUNTRIES_URL + "/1"))
                .andExpect(status().isNotFound());
    }

}
