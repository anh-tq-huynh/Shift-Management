package com.example.shift_mgmt.service;

import com.example.shift_mgmt.entity.Client;
import com.example.shift_mgmt.entity.Employee;
import com.example.shift_mgmt.entity.Shift;
import com.example.shift_mgmt.entity.ShiftStatus;
import com.example.shift_mgmt.repository.ClientRepository;
import com.example.shift_mgmt.repository.EmployeeRepository;
import com.example.shift_mgmt.repository.ShiftRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@Transactional
public class ShiftServiceImpl extends GenericServiceImpl<Shift, Long> implements ShiftService {
    private final ShiftRepository shiftRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public ShiftServiceImpl(ShiftRepository shiftRepository,
                            ClientRepository clientRepository,
                            EmployeeRepository employeeRepository){
        super(shiftRepository);
        this.shiftRepository = shiftRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }
    public Shift createShift(Long clientId, LocalDate date, LocalTime start, LocalTime end){
        Client cli = clientRepository.findClientByClientId(clientId);


        LocalDateTime startTime = LocalDateTime.of(date,start);
        LocalDateTime endTime = LocalDateTime.of(date, end);
        Duration duration = Duration.between(startTime, endTime);

        double durationInHour = duration.toMinutes() /60.0;
        if (cli.getHourQuota() < durationInHour){
            throw new RuntimeException("Client doesn't have enough hour quota");
        }
        else{
            Shift newShift = new Shift();
            newShift.setStartDateTime(startTime);
            newShift.setEndDateTime(endTime);
            return newShift;
        }
    }


    @Override
    public void markAsDone(Long empId, Long shiftID){
        Employee emp = employeeRepository.findEmployeeByEmployeeId(empId);
        Shift shift = shiftRepository.findShiftByShiftId(shiftID);

        if (shift.getEmployee().getEmployeeId() == emp.getEmployeeId()){
            shift.setStatus(ShiftStatus.COMPLETED);
            long time = shift.getDuration();
            emp.setEmployeeTotalHours(emp.getEmployeeTotalHours() + time);
        }
        else{
            throw new RuntimeException("Wrong employee ID!");
        }

        shiftRepository.save(shift);
        employeeRepository.save(emp);
    }
}
