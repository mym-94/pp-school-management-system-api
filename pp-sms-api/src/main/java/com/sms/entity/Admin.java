package com.sms.entity;

import javax.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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

    @Schema(description = "Title of admin user.", example = "Head Master", required = false)
    @Column(name = "title", nullable = false)
    private String title;
}