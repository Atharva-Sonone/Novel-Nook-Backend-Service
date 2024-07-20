package com.atharvaworks.novelnook.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ValidationResponse {
    private final HttpStatus status;
    private final Map<String,String> errors;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private final ZonedDateTime timeStamp;
}
