package com.example.shift_mgmt.repository;

import com.example.shift_mgmt.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends JpaRepository<Shift,Long> {
    Shift findShiftByShiftId(long shiftId);
}
