package com.vantuan.patientmanagement.common.user.model;

import java.time.Instant;

import com.vantuan.patientmanagement.common.enums.UserRole;

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
    public static final int FIRST_NAME_MAX_SIZE = 150;
    public static final int LAST_NAME_MAX_SIZE = 150;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = FIRST_NAME_MAX_SIZE)
    private String firstName;

    @NotNull
    @Size(max = LAST_NAME_MAX_SIZE)
    private String lastName;

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
