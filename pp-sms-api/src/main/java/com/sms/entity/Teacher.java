package com.sms.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Table(name = "teachers")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Teacher extends User {

    public Teacher(String username, String password, String degree, int yearsOfExperience, boolean enabled) {
        this.username = username;
        this.password = password;
        this.degree = degree;
        this.yearsOfExperience = yearsOfExperience;
        this.enabled = enabled;
    }

    @Schema(description = "Teacher's degree.", example = "junior teacher", required = true)
    @NotNull
    @Column(name = "degree")
    private String degree;

    @Schema(description = "Teacher's years of experience number.", example = "1", required = true)
    @NotNull
    @Column(name = "years_of_experience")
    private int yearsOfExperience;

}