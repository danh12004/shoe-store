package com.example.project.dto.response;

import com.example.project.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String password;
    String firstName;
    String lastName;
    String gender;
    LocalDate birthday;
    String address;
    String email;
    String phoneNumber;
    Boolean enable = true;
    Set<Role> roles;
}
