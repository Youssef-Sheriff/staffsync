package com.youssef.staffsync.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmployeeUpdate(
        @NotNull(message = " is required.")
        @Size(min = 3, max = 10, message = "min is 3 chars, and max is 10 chars")
        String firstName,

        @NotNull(message = " is required.")
        @Size(min = 3, max = 10, message = "min is 3 chars, and max is 10 chars")
        String lastName,

        @NotNull(message = " is required.")
        @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Invalid phone number")
        String phoneNumber,

        @NotNull(message = " is required.")
        @Size(min = 2, max = 20, message = "min is 2 chars, and max is 20 chars")
        String position
) {
}
