package com.sms.service;

import com.sms.entity.Country;
import com.sms.exception.EntityNotFoundException;
import com.sms.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>Country Service Implementation Classs</h1>
 *
 * @author Mahmoud Yahia
 * @since January 2020
 * @version 1.0.0
 */

@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(long id) {
        return countryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Country.class.getName(), id));
    }

    @Override
    public Country saveOrUpdate(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public void deleteById(long id) {
        try {
            countryRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException(Country.class.getName(), id);
        }
    }

}
