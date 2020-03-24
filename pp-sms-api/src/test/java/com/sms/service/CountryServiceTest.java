package com.sms.service;

import com.sms.entity.Country;
import com.sms.repository.CountryRepository;
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
public class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService = new CountryServiceImpl(countryRepository);

    private static final String ENTITY_NAME = "test country";

    @Test
    void testFindAll() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country(1, ENTITY_NAME, new Date()));
        when(countryRepository.findAll()).thenReturn(countries);

        assertEquals(1, countryService.findAll().size());
    }

    @Test
    void testFindByIdResourceExist() {
        Country country = new Country(1, ENTITY_NAME, new Date());
        Optional<Country> singleCountryById = Optional.of(country);
        when(countryRepository.findById(anyLong())).thenReturn(singleCountryById);

        assertEquals(ENTITY_NAME, countryService.findById(1).getName());
    }

    @Test
    void testFindByIdResourceNotExist() {
        doThrow(new EntityNotFoundException(Country.class.getName(), 1)).when(countryRepository).findById(1L);

        assertThrows(EntityNotFoundException.class, () -> countryService.findById(1));
    }

    @Test
    void testSaveOrUpdate() {
        Country country = new Country(1, ENTITY_NAME, new Date());
        countryService.saveOrUpdate(country);

        verify(countryRepository).save(country);
    }

    @Test
    void testDeleteById() {
        countryService.deleteById(1L);

        verify(countryRepository).deleteById(1L);
    }

    @Test
    void testDeleteByIdResourceNotExist() {
        doThrow(new EntityNotFoundException(Country.class.getName(), 1)).when(countryRepository).deleteById(1L);

        assertThrows(EntityNotFoundException.class, () -> countryService.deleteById(1));
    }

}
