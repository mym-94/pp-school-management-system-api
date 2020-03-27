package com.sms.service;

import com.sms.entity.Subject;
import com.sms.exception.EntityNotFoundException;
import com.sms.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectService subjectService = new SubjectServiceImpl(subjectRepository);

    private static final String ENTITY_NAME = "test Subject";

    @Test
    void testFindAll() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject(1, ENTITY_NAME, new Date()));
        when(subjectRepository.findAll()).thenReturn(subjects);

        assertEquals(1, subjectService.findAll().size());
    }

    @Test
    void testFindByIdResourceExist() {
        Subject subject = new Subject(1, ENTITY_NAME, new Date());
        Optional<Subject> singleSubjectById = Optional.of(subject);
        when(subjectRepository.findById(anyLong())).thenReturn(singleSubjectById);

        assertEquals(ENTITY_NAME, subjectService.findById(1).getName());
    }

    @Test
    void testFindByIdResourceNotExist() {
        doThrow(new EntityNotFoundException(Subject.class.getName(), 1)).when(subjectRepository).findById(1L);

        assertThrows(EntityNotFoundException.class, () -> subjectService.findById(1));
    }

    @Test
    void testSaveOrUpdate() {
        Subject subject = new Subject(1, ENTITY_NAME, new Date());
        subjectService.saveOrUpdate(subject);

        verify(subjectRepository).save(subject);
    }

    @Test
    void testDeleteById() {
        subjectService.deleteById(1L);

        verify(subjectRepository).deleteById(1L);
    }

    @Test
    void testDeleteByIdResourceNotExist() {
        doThrow(new EntityNotFoundException(Subject.class.getName(), 1)).when(subjectRepository).deleteById(1L);

        assertThrows(EntityNotFoundException.class, () -> subjectService.deleteById(1));
    }

}
