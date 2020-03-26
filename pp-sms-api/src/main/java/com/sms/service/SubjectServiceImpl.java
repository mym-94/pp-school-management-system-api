package com.sms.service;

import com.sms.entity.Subject;
import com.sms.exception.EntityNotFoundException;
import com.sms.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject findById(long id) {
        return subjectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Subject.class.getName(), id));
    }

    @Override
    public Subject saveOrUpdate(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void deleteById(long id) {
        try {
            subjectRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException(Subject.class.getName(), id);
        }
    }

}
