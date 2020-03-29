package com.sms.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@AllArgsConstructor
public class Teacher {

    public Teacher(String uuid, String username, String password, int yearsOfExperience, String degree) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.yearsOfExperience = yearsOfExperience;
        this.degree = degree;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "username")
    private String username;

    @JoinColumn(name="years_of_experience")
    private int yearsOfExperience;

    @Column(name = "degree")
    private String degree;

    @OneToOne(mappedBy = "teacher")
    private User user;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

    @Transient
    private String password;

}


