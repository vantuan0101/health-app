package com.vantuan.careplanmanagement.read;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public final class PhysicalActivityDTOs {

    @Getter
    @Setter
    abstract static class Base {

        private Long id;

        private Short daysOfModerateActivity;

        private Short minutesOfModerateActivity;

        private Short daysOfVigorousActivity;

        private Short minutesOfVigorousActivity;

    }

    @Getter
    @Setter
    public static class Full extends Base {
    }

}
