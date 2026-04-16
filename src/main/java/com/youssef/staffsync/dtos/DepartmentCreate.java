package com.youssef.staffsync.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DepartmentCreate(
        @NotNull(message = " is required.")
        @Size(min = 2, message = "min is 2 chars.")
        String name
) {
}
