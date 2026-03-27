package com.hriddhi.shiftmanager;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Integer> {

    List<Shift> findByEmployeeName(String employeeName);
}
