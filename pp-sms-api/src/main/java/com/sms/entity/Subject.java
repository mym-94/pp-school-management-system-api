package com.sms.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Table(name = "subjects")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    public Subject(long id, String name, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.updatedAt = updatedAt;
    }

    @Schema(description = "Unique identifier of the subject.", example = "1")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "name of the subject.", example = "Mathematics-2")
    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="academic_year_id", nullable=false)
    private AcademicYear academicYear;

    @ManyToOne
    @JoinColumn(name="teacher_id", nullable=false)
    private Teacher teacher;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

}