package com.sms.service;

import com.sms.entity.Country;

import java.util.List;

/**
 * <h1>Country Service Base Interface</h1>
 *
 * @author Mahmoud Yahia
 * @since January 2020
 * @version 1.0.0
 */

public interface CountryService {

    List<Country> findAll();
    Country findById(long id);
    Country saveOrUpdate(Country country);
    void deleteById(long id);

}
