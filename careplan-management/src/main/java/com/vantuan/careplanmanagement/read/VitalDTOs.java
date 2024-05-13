package com.vantuan.careplanmanagement.read;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public final class VitalDTOs {

    @Getter
    @Setter
    abstract static class Base {

        private Long id;

        private Short currentHeight;

        private Short currentWeight;

        private Short heartRate;

        private Float temperature;

        private Short oxygenSaturation;

        private Short systolicBloodPressure;

        private Short diastolicBloodPressure;

    }

    @Getter
    @Setter
    public static class Full extends Base {
    }

}
