package com.sms.exception;

/**
 * <h1>Country Not Found Exception Class</h1>
 *
 * @author Mahmoud Yahia
 * @since January 2020
 * @version 1.0.0
 */

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String className, long id) {
        super(className + " not found. id: " + id);
    }

}
