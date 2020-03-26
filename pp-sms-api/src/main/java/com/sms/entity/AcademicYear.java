package com.sms.entity;

import javax.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.Date;

/**
 * <h1>AcademicYear Entity</h1>
 *
 * @author Mahmoud Yahia
 * @since January 2020
 * @version 1.0.0
 */

@Entity
@Data
@Table(name = "academic_years")
@NoArgsConstructor
@AllArgsConstructor
public class AcademicYear {

    @Schema(description = "Unique identifier of the academic year.", example = "1")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Name of the Academic Year.", example = "First Year Primary", required = true)
    @Column(name = "name")
    private String name;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private Date updatedAt;

}
