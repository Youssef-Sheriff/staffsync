package com.youssef.staffsync.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record EmployeeCreate(
        
        @NotNull(message = "first name is required.")
        @Size(min = 3, max = 10, message = "min is 3 chars, and max is 10 chars")
        String firstName,

        @NotNull(message = "last name is required.")
        @Size(min = 3, max = 10, message = "min is 3 chars, and max is 10 chars")
        String lastName,

        @Email(message = "Invalid email format.")
        @NotNull(message = "Email is required.")
        String email,

        @NotNull(message = "phone number is required.")
        @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Invalid phone number")
        String phoneNumber,

        @NotNull(message = "hireDate is required.")
        @PastOrPresent(message = "Hire date cannot be in the future.")
        LocalDate hireDate,

        @NotNull(message = "position is required.")
        @Size(min = 2, max = 20, message = "min is 2 chars, and max is 20 chars")
        String position
) {
}
