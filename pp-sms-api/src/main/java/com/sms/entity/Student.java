package com.sms.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

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

    @NotNull
    @ManyToOne
    @JoinColumn(name="academic_year_id", nullable=false)
    private AcademicYear academicYear;

}