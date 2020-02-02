package com.sms.exception;

/**
 * <h1>Country Not Found Exception Class</h1>
 *
 * @author Mahmoud Yahia
 * @since January 2020
 * @version 1.0.0
 */

public class CountryNotFoundException extends RuntimeException{

    public CountryNotFoundException(long id) {
        super("country not found. id: " + id);
    }

}
