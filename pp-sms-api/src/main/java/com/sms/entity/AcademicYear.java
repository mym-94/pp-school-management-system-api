package com.sms.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.UpdateTimestamp;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * <h1>AcademicYear Entity</h1>
 *
 * @author Mahmoud Yahia
 * @since January 2020
 * @version 1.0.0
 */

@Entity
@Setter
@Getter
@Table(name = "academic_years")
@NoArgsConstructor
@AllArgsConstructor
public class AcademicYear {

    public AcademicYear(long id, String name, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.updatedAt = updatedAt;
    }

    @Schema(description = "Unique identifier of the academic year.", example = "1")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Schema(description = "Name of the Academic Year.", example = "First Year Primary", required = true)
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy="academicYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Subject> subjects;

    @JsonIgnore
    @OneToMany(mappedBy="academicYear", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Student> students;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

}
