package com.sms.service;

import com.sms.entity.Subject;

import java.util.List;

/**
 * <h1>Country Service Base Interface</h1>
 *
 * @author Mahmoud Yahia
 * @since January 2020
 * @version 1.0.0
 */

public interface SubjectService {

    List<Subject> findAll();
    Subject findById(long id);
    Subject saveOrUpdate(Subject subject);
    void deleteById(long id);

}
