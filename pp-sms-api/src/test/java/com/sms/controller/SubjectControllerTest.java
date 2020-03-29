package com.sms.controller;

import com.sms.entity.Subject;
import com.sms.exception.EntityNotFoundException;
import com.sms.service.SubjectService;
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
public class SubjectControllerTest {

    private static final String SUBJECT_URL = "/v1/subjects";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectService subjectService;

    @Test
    void testFindAll() throws Exception {
        // given
        Date testDateObject = new Date(1583413273);
        Subject subject = new Subject(1, "test", testDateObject);
        List<Subject> subjects = Arrays.asList(subject);
        given(subjectService.findAll()).willReturn(subjects);

        // when + then
        mockMvc.perform(get(SUBJECT_URL))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'name': 'test', 'updatedAt': \"1970-01-19T07:50:13.273+0000\"}]"));
    }

    @Test
    void testFindByIdResourceExist() throws Exception {
        // given
        Date testDateObject = new Date(1583413273);
        Subject subject = new Subject(1, "test", testDateObject);
        given(subjectService.findById(1)).willReturn(subject);

        // when + then
        mockMvc.perform(get(SUBJECT_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'name': 'test', 'updatedAt': \"1970-01-19T07:50:13.273+0000\"}"));
    }

    @Test
    void testFindByIdResourceNotExist() throws Exception {
        // given
        given(subjectService.findById(1)).willThrow(new EntityNotFoundException(Subject.class.getName(), 1));

        // when + then
        mockMvc.perform(get(SUBJECT_URL + "/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSave() throws Exception{
        // given
        Date testDateObject = new Date(1583413273);
        Subject subject = new Subject(1, "test", testDateObject);
        given(subjectService.saveOrUpdate(any(Subject.class))).willReturn(subject);

        // when + then
        mockMvc.perform(post(SUBJECT_URL)
                .content("{\"name\": \"test\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'name': 'test', 'updatedAt': \"1970-01-19T07:50:13.273+0000\"}"));
    }

    @Test
    void testUpdate() throws Exception{
        // given
        Date testDateObject = new Date(1583413273);
        Subject subject = new Subject(1, "test", testDateObject);
        given(subjectService.saveOrUpdate(any(Subject.class))).willReturn(subject);

        // when + then
        mockMvc.perform(put(SUBJECT_URL)
                .content("{\"id\": 1,\"name\": \"test\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'name': 'test', 'updatedAt': \"1970-01-19T07:50:13.273+0000\"}"));
    }

    @Test
    void testDeleteByIdResourceExist() throws Exception {
        //when + then
        mockMvc.perform(delete(SUBJECT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(subjectService).deleteById(1);
    }

    @Test
    void testDeleteByIdResourceNotExist() throws Exception {
        // given
        doThrow(new EntityNotFoundException(Subject.class.getName(), 1)).when(subjectService).deleteById(1);

        // when + then
        mockMvc.perform(delete(SUBJECT_URL + "/1"))
                .andExpect(status().isNotFound());
    }

}
