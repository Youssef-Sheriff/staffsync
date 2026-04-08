package com.youssef.staffsync.entities;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Setter
@Getter
@AllArgsConstructor
public class Employee {
    private UUID id;

    @NotNull(message = "first name is required.")
    @Size(min = 3, max = 10, message = "min is 3 chars, and max is 10 chars")
    private String firstName;

    @NotNull(message = "last name is required.")
    @Size(min = 3, max = 10, message = "min is 3 chars, and max is 10 chars")
    private String lastName;

    @Email(message = "Invalid email format.")
    @NotNull(message = "Email is required.")
    private String email;

    @NotNull(message = "phone number is required.")
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Invalid phone number")
    private String phoneNumber;

    @NotNull(message = "hireDate is required.")
    @PastOrPresent(message = "Hire date cannot be in the future.")
    private LocalDate hireDate;

    @NotNull(message = "position is required.")
    @Size(min = 2, max = 20, message = "min is 2 chars, and max is 20 chars")
    private String position;

    private UUID departmentId;


}
