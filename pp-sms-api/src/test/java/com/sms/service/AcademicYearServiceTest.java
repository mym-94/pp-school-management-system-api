package com.sms.service;

import com.sms.entity.AcademicYear;
import com.sms.repository.AcademicYearRepository;
import com.sms.exception.EntityNotFoundException;
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
public class AcademicYearServiceTest {

    @Mock
    private AcademicYearRepository academicYearRepository;

    @InjectMocks
    private AcademicYearService academicYearService = new AcademicYearServiceImpl(academicYearRepository);

    private static final String ENTITY_NAME = "test academic year";

    @Test
    void testFindAll() {
        List<AcademicYear> academicYears = new ArrayList<>();
        academicYears.add(new AcademicYear(1, ENTITY_NAME, new Date()));
        when(academicYearRepository.findAll()).thenReturn(academicYears);

        assertEquals(1, academicYearService.findAll().size());
    }

    @Test
    void testFindByIdResourceExist() {
        AcademicYear academicYear = new AcademicYear(1, ENTITY_NAME, new Date());
        Optional<AcademicYear> singleAcademicYearById = Optional.of(academicYear);
        when(academicYearRepository.findById(anyLong())).thenReturn(singleAcademicYearById);

        assertEquals(ENTITY_NAME, academicYearService.findById(1).getName());
    }

    @Test
    void testFindByIdResourceNotExist() {
        doThrow(new EntityNotFoundException(AcademicYear.class.getName(), 1)).when(academicYearRepository).findById(1L);

        assertThrows(EntityNotFoundException.class, () -> academicYearService.findById(1));
    }

    @Test
    void testSaveOrUpdate() {
        AcademicYear academicYear = new AcademicYear(1, ENTITY_NAME, new Date());
        academicYearService.saveOrUpdate(academicYear);

        verify(academicYearRepository).save(academicYear);
    }

    @Test
    void testDeleteById() {
        academicYearService.deleteById(1L);

        verify(academicYearRepository).deleteById(1L);
    }

    @Test
    void testDeleteByIdResourceNotExist() {
        doThrow(new EntityNotFoundException(AcademicYear.class.getName(), 1)).when(academicYearRepository).deleteById(1L);

        assertThrows(EntityNotFoundException.class, () -> academicYearService.deleteById(1));
    }

}
