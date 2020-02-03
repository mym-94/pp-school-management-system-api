package com.sms.entity;

import javax.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * <h1>Country Entity</h1>
 *
 * @author Mahmoud Yahia
 * @since January 2020
 * @version 1.0.0
 */

@Entity
@Data
@Table(name = "countries")
@NoArgsConstructor
public class Country {

    @Schema(description = "Unique identifier of the Country.", example = "1")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Name of the Country.", example = "EGYPT", required = true)
    @Column(name = "name")
    private String name;

    @Schema(description = "Country information updated at Date.", example = "2020-02-03 12:50:50")
    @Column(name = "updated_at")
    private String updatedAt;

}
