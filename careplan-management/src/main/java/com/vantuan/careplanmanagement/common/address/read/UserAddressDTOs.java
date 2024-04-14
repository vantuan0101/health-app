package com.vantuan.careplanmanagement.common.address.read;

import com.vantuan.careplanmanagement.common.enums.Country;
import com.vantuan.careplanmanagement.common.enums.Region;
import com.vantuan.common.mapper.StructMapper;
import com.vantuan.careplanmanagement.common.address.model.entity.UserAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;

@NoArgsConstructor
public final class UserAddressDTOs {

    @Getter
    @Setter
    abstract static class Base {

        @Schema(example = "East 64th Street")
        private String address;

        @Schema(example = "New York")
        private String city;

        private Country country;

        @Schema(example = "10122")
        private String zipCode;

        private Region region;

    }

    @Getter
    @Setter
    public static class Full extends Base {
    }

    @Mapper
    public abstract static class UserAddressFullMapper extends StructMapper<UserAddress, Full> {
    }

}
