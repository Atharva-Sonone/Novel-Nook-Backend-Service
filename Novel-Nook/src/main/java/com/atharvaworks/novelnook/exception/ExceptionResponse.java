package com.atharvaworks.novelnook.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    private final HttpStatus status;
    private final String message;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private final ZonedDateTime timeStamp;
}
