package com.sms.controller;

import com.sms.entity.AcademicYear;
import com.sms.entity.Country;
import com.sms.service.AcademicYearService;
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
public class AcademicYearControllerTest {

    private static final String ACADEMIC_YEAR_URL = "/v1/academicyears";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AcademicYearService academicYearService;

    @Test
    void testFindAll() throws Exception {
        // given
        Date testDateObject = new Date(1583413273);
        AcademicYear academicYear = new AcademicYear(1, "test", testDateObject);
        List<AcademicYear> academicYears = Arrays.asList(academicYear);
        given(academicYearService.findAll()).willReturn(academicYears);

        // when + then
        mockMvc.perform(get(ACADEMIC_YEAR_URL))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'name': 'test', 'updatedAt': \"1970-01-19T07:50:13.273+0000\"}]"));
    }

    @Test
    void testFindByIdResourceExist() throws Exception {
        // given
        Date testDateObject = new Date(1583413273);
        AcademicYear academicYear = new AcademicYear(1, "test", testDateObject);
        given(academicYearService.findById(1)).willReturn(academicYear);

        // when + then
        mockMvc.perform(get(ACADEMIC_YEAR_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'name': 'test', 'updatedAt': \"1970-01-19T07:50:13.273+0000\"}"));
    }


    @Test
    void testFindByIdResourceNotExist() throws Exception {
        // given
        given(academicYearService.findById(1)).willThrow(new EntityNotFoundException(Country.class.getName(), 1));

        // when + then
        mockMvc.perform(get(ACADEMIC_YEAR_URL + "/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSaveOrUpdate() throws Exception{
        // given
        Date testDateObject = new Date(1583413273);
        AcademicYear academicYear = new AcademicYear(1, "test", testDateObject);
        given(academicYearService.saveOrUpdate(any(AcademicYear.class))).willReturn(academicYear);

        // when + then
        mockMvc.perform(post(ACADEMIC_YEAR_URL)
                .content("{\"id\": 11,\"email\": \"testagain@again.again\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'name': 'test', 'updatedAt': \"1970-01-19T07:50:13.273+0000\"}"));
    }

    @Test
    void testDeleteByIdResourceExist() throws Exception {
        //when + then
        mockMvc.perform(delete(ACADEMIC_YEAR_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(academicYearService).deleteById(1);
    }

    @Test
    void testDeleteByIdResourceNotExist() throws Exception {
        // given
        doThrow(new EntityNotFoundException(Country.class.getName(), 1)).when(academicYearService).deleteById(1);

        // when + then
        mockMvc.perform(delete(ACADEMIC_YEAR_URL + "/1"))
                .andExpect(status().isNotFound());
    }

}
