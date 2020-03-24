package com.sms.service;

import com.sms.entity.AcademicYear;
import com.sms.exception.EntityNotFoundException;
import com.sms.repository.AcademicYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>AcademicYear Service Implementation Classs</h1>
 *
 * @author Mahmoud Yahia
 * @since January 2020
 * @version 1.0.0
 */

@Service
public class AcademicYearServiceImpl implements AcademicYearService {

    private AcademicYearRepository academicYearRepository;

    @Autowired
    public AcademicYearServiceImpl(AcademicYearRepository academicYearRepository){
        this.academicYearRepository = academicYearRepository;
    }

    @Override
    public List<AcademicYear> findAll() {
        return academicYearRepository.findAll();
    }

    @Override
    public AcademicYear findById(long id) {
        return academicYearRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(AcademicYear.class.getName(), id));
    }

    @Override
    public AcademicYear saveOrUpdate(AcademicYear academicYear) {
        return academicYearRepository.save(academicYear);
    }

    @Override
    public void deleteById(long id) {
        try {
            academicYearRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException(AcademicYear.class.getName(), id);
        }
    }

}
