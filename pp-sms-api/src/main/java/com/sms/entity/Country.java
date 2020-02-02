package com.sms.entity;

import javax.persistence.*;

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

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "updated_at")
    private String updatedAt;

}
