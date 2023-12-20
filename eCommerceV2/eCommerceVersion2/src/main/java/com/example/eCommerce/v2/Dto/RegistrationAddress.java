package com.example.eCommerce.v2.Dto;

import lombok.Getter;
import lombok.Setter;

    @Getter @Setter
    public class RegistrationAddress {

        @Getter @Setter
        private String country;
        @Getter @Setter
        private String city;

        @Getter @Setter
        private RegistrationBody registrationBody;
    }
