package com.consumer.management.api.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccompanimentRequest {
    private Integer id;
    private String accompanimentName;
    private String status;
    private Date dtRegister;
}
