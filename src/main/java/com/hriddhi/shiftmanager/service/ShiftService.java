package com.hriddhi.shiftmanager.service;

import com.hriddhi.shiftmanager.*;
import com.hriddhi.shiftmanager.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import com.hriddhi.shiftmanager.ShiftRepository;
@Service
public class ShiftService {
    private final ShiftRepository shiftRepository;
    private final UserRepository userRepository;

    public ShiftService(ShiftRepository shiftRepository, UserRepository userRepository) {
        this.shiftRepository = shiftRepository;
        this.userRepository = userRepository;
    }

    public String assignShift(Shift shift) {

        boolean found = false;


        for (Users u : userRepository.findAll()) {
            if (u.username.equals(shift.employeeName)) {
                found = true;
                break;
            }
        }

        if (!found) {
            throw new BadRequestException("User not found!");
        }

        LocalTime newStart = LocalTime.parse(shift.startTime);
        LocalTime newEnd = LocalTime.parse(shift.endTime);

        if (newStart.compareTo(newEnd) >= 0) {
            throw new BadRequestException("Invalid shift time!");
        }

        List<Shift> shifts = shiftRepository.findByEmployeeName(shift.employeeName);
        for (Shift s : shifts) {
            if (s.employeeName.equals(shift.employeeName)) {

                LocalTime existingStart = LocalTime.parse(s.startTime);
                LocalTime existingEnd = LocalTime.parse(s.endTime);

                if (newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart)) {
                    throw new BadRequestException("Shift conflict detected!");
                }
            }
        }
      shiftRepository.save(shift);
        return "Shift assigned to " + shift.employeeName;
    }
    public List<Shift> getShifts() {
        return shiftRepository.findAll();
    }
}