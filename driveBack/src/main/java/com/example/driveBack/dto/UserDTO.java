package com.example.driveBack.dto;

import com.example.driveBack.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String email;
    private String firstName;
    private String lastName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date birthday;

    public UserDTO(User user){
        email = user.getEmail();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        birthday = user.getBirthday();
    }
}
