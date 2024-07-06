package com.patito.cdod.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class DetallesErrorDTO {

    private Date timestamp;
    private String message;
    private String details;
    
}
