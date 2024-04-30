package com.muvit.MUVIT.api.dto.request;
import com.muvit.MUVIT.util.enums.RolEnum;
import com.muvit.MUVIT.util.enums.stateServiceEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceRequest {
    private RolEnum typeService;
    private String distance;
    private int assistant;
    private Double price;
    private String startPoint;
    private String finalPoint;
    private stateServiceEnum statusService;
}
