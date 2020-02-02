package com.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO: SMS-XXXX: secure application endpoints.
@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class}
)
public class SchoolManagementSysemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementSysemApplication.class, args);
	}

}
