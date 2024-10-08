package com.lcwd.electronicStore.dtos;

import com.lcwd.electronicStore.valid.ImageNameValid;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String userId;
   @Size(min = 3,max = 10,message = "invalid Name!!")
    private String name;
   // @Email(message = "Invalid User Email !!")//proper valid na kr rha h
   @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Invalid Email")
   @NotBlank(message = "required  !!!")
   private String email;

    @NotBlank(message = "Password is required")
    private String password;
    @Size(min = 3,max = 6)
    private  String gender;
      @NotBlank(message = "write something about yourself")
    private  String about;
  //@Pattern
    //@CustomValidator
    @ImageNameValid
    private  String imageName;

}
