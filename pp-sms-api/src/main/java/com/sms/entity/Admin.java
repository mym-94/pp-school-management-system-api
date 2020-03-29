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

@Table(name = "admins")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Admin extends User {

    public Admin(String username, String password, String title, boolean enabled) {
        this.username = username;
        this.password = password;
        this.title = title;
        this.enabled = enabled;
    }

    @Column(name = "title", nullable = false)
    private String title;
}