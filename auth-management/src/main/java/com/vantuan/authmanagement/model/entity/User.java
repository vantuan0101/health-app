package com.vantuan.authmanagement.model.entity;

import java.time.Instant;

import com.vantuan.authmanagement.enums.UserRole;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
@SuperBuilder(toBuilder = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Email
    @Pattern(regexp = "(^[0-9A-Za-z][\\w.\\-]+@[\\w]+\\.[\\w]\\S+\\w)$", message = "Invalid email!")
    @NotBlank
    private String email;

    @NotNull
    @Size(min = 8)
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull
    private UserRole userRole = UserRole.USER;

    @CreationTimestamp
    @Column(nullable = false)
    @JsonProperty(access = Access.READ_ONLY)
    private Instant createdAt;

    public String getUserRole() {
        return String.valueOf(userRole);
    }
}
