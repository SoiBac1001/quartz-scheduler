package com.example.demospringquartz.payload;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class EmailResponse {
    private boolean success;
    private String jobId;
    private String jobGroup;
    private String message;
}
