package com.sms.service;

import com.sms.entity.AcademicYear;

import java.util.List;

/**
 * <h1>AcademicYear Service Base Interface</h1>
 *
 * @author Mahmoud Yahia
 * @since January 2020
 * @version 1.0.0
 */

public interface AcademicYearService {

    List<AcademicYear> findAll();
    AcademicYear findById(long id);
    AcademicYear saveOrUpdate(AcademicYear academicYear);
    void deleteById(long id);

}
