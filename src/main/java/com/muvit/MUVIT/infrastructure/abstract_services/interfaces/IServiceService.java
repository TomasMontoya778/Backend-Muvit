package com.muvit.MUVIT.infrastructure.abstract_services.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.muvit.MUVIT.api.dto.request.ServiceRequest;
import com.muvit.MUVIT.api.dto.response.ServiceResponse;

public interface IServiceService extends CrudService<ServiceRequest, ServiceResponse, String> {
    public ServiceResponse getById(String id);

    public Optional<ServiceResponse> getActiveServiceByUserId(String userId);

    public Page<ServiceResponse> getInactiveServiceByUserId(String userId, Pageable pageable);

    public Page<ServiceResponse> getAllActiveService(Pageable pageable);
    
    public Page<ServiceResponse> getInactiveServiceByDriverId(String driverId, Pageable pageable);

    public Page<ServiceResponse> getAvailableServiceByDriverParams(String size, int assistant, Pageable pageable);
}
