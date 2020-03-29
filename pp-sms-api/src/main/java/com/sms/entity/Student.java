package com.sms.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Table(name = "students")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Student extends User {

    public Student(String username, String password, AcademicYear academicYear, boolean enabled) {
        this.username = username;
        this.password = password;
        this.academicYear = academicYear;
        this.enabled = enabled;
    }

    @ManyToOne
    @JoinColumn(name="academic_year_id", nullable=false)
    private AcademicYear academicYear;

}