package com.clvt.springJpaSpecification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
public class ResponseDto {
    private Object data;
    private int httpStatus;
    private String message;
}
