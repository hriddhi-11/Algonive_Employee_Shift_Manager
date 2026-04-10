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
            if (u.getUsername().equals(shift.getUser().getUsername())) {
                shift.setUser(u);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new BadRequestException("User not found!");
        }

        LocalTime newStart = LocalTime.parse(shift.getStartTime());
        LocalTime newEnd = LocalTime.parse(shift.getEndTime());

        if (newStart.compareTo(newEnd) >= 0) {
            throw new BadRequestException("Invalid shift time!");
        }

        List<Shift> shifts = shiftRepository.findByUserId(shift.getUser());
        for (Shift s : shifts) {
            if (s.getUser().getUsername().equals(shift.getUser().getUsername())) {

                LocalTime existingStart = LocalTime.parse(s.getStartTime());
                LocalTime existingEnd = LocalTime.parse(s.getEndTime());

                if (newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart)) {
                    throw new BadRequestException("Shift conflict detected!");
                }
            }
        }
      shiftRepository.save(shift);
        return "Shift assigned to " + shift.getUser().getUsername();
    }
    public List<Shift> getShifts() {
        return shiftRepository.findAll();
    }
}