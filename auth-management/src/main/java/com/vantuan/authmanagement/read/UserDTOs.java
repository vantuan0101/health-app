package com.vantuan.authmanagement.read;

import com.vantuan.authmanagement.common.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
public class UserDTOs {
    @Getter
    @Setter
    abstract static class Base {

        private Long id;

        @Schema(example = "John")
        private String firstName;

        @Schema(example = "Kowalski")
        private String lastName;

        private UserRole userRole;

        private Instant createdAt;

    }

    @Getter
    @Setter
    public static class Full extends Base {

    }

    @Getter
    @Setter
    public static class Short extends Base {
    }
}
