package com.example.demospringquartz.controller;

import com.example.demospringquartz.json.Staff;
import com.example.demospringquartz.payload.EmailRequest;
import com.example.demospringquartz.payload.EmailResponse;
import com.example.demospringquartz.service.EmailSchedulerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
public class EmailSchedulerController {
    private final EmailSchedulerService emailSchedulerService;

    @PostMapping("/schedule/email")
    public ResponseEntity<EmailResponse> scheduleEmail(@RequestBody @Valid EmailRequest emailRequest) {
        return new ResponseEntity<>(this.emailSchedulerService.scheduleEmail(emailRequest), HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Get API test - pass");
    }

    @GetMapping("/test-2")
    public ResponseEntity<Staff> test2() {
        return ResponseEntity.ok(createStaff());
    }

    private Staff createStaff() {

        Staff staff = new Staff();

        staff.setName("mkyong");
        staff.setAge(38);
        staff.setPosition(new String[]{"Founder", "CTO", "Writer"});
        Map<String, BigDecimal> salary = new HashMap() {{
            put("2010", new BigDecimal(10000));
            put("2012", new BigDecimal(12000));
            put("2018", new BigDecimal(14000));
        }};
        staff.setSalary(salary);
        staff.setSkills(Arrays.asList("java", "python", "node", "kotlin"));

        return staff;

    }
}
