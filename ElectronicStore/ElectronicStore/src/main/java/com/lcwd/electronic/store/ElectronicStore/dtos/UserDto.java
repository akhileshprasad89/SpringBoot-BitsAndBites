package com.lcwd.electronic.store.ElectronicStore.dtos;

import com.lcwd.electronic.store.ElectronicStore.validate.ImageNameValid;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String userId;
    @Size(min = 3,max = 15,message = "Invalid Name !!")
    private String name;
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",message = "EMail is not valid")
    @NotBlank(message = "Email is required !!")
    private String email;
    @NotBlank(message = "Password is Required")
    private String password;
    @Size(min = 3,max = 6,message = "Invalid Gender !!")
    private String gender;
    @NotBlank(message = "Write Something about you !!")
    private String about;
    @ImageNameValid
    private String imageName;
}
