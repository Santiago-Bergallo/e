package com.example.eCommerce.v2.Dto;

import com.example.eCommerce.v2.model.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class RegistrationBody {

    @Getter @Setter
    private String lastName;
    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String email;

    @Getter @Setter @NotBlank
    @NotBlank @Size(max = 32, min = 6) @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    private String password;
    @Getter @Setter
    private List<RegistrationAddress> addresses = new ArrayList<>();


}
