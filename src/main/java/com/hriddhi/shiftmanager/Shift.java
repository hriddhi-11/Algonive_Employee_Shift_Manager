package com.hriddhi.shiftmanager;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;

@Entity
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @NotBlank(message = "Employee name is required")
    public String employeeName;
    @NotBlank(message = "Start time is required")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Time must be HH:mm")
    public String startTime;
    @NotBlank(message = "End time is required")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Time must be HH:mm")
    public String endTime;
}
