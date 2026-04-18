package com.youssef.staffsync.dtos;

import com.youssef.staffsync.entities.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record LeaveRequestCreate(
        @NotNull(message = " is required.")
        @Size(min = 2, max = 100, message = "min is 2 chars & max is 100 chars.")
        String reason,

        @NotNull(message = " is required.")
        @FutureOrPresent(message = " should be now or in the future.")
        LocalDate startDate,

        @NotNull(message = " is required.")
        @FutureOrPresent(message = " should be now or in the future.")
        LocalDate endDate

) {
}
