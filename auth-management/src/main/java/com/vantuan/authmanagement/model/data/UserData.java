package com.vantuan.authmanagement.model.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
public final class UserData {

    @Getter
    @Setter
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public abstract static class Base {
        @NotNull
        private String email;

        @NotNull
        private String password;

        @NotNull
        private String confirmPass;
    }

    @Getter
    @Setter
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class Edit extends Base {
    }

    @Getter
    @Setter
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class Create extends Base {

    }
}
