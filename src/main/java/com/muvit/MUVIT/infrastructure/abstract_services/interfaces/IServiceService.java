package com.muvit.MUVIT.infrastructure.abstract_services.interfaces;

import com.muvit.MUVIT.api.dto.request.ServiceRequest;
import com.muvit.MUVIT.api.dto.response.ServiceResponse;

public interface IServiceService extends CrudService<ServiceRequest, ServiceResponse, String> {
    public ServiceResponse getById(String id);
}
