package com.example.project.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeCreateRequest {
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
    String roleId;
}
