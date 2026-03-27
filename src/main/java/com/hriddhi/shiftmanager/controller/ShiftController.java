package com.hriddhi.shiftmanager.controller;

import com.hriddhi.shiftmanager.Shift;
import com.hriddhi.shiftmanager.dto.ApiResponse;
import com.hriddhi.shiftmanager.service.ShiftService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
@RestController
@RequestMapping("/shifts")
public class ShiftController {


    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    };
    @GetMapping
    public ApiResponse getShifts() {
        return new ApiResponse("SUCCESS", "All shifts", shiftService.getShifts());
    }
    @PostMapping
    public ApiResponse assignShift(@Valid @RequestBody Shift shift) {
        String message = shiftService.assignShift(shift);
        return new ApiResponse("SUCCESS", message, shift);
    }
}
