package com.muvit.MUVIT.infrastructure.abstract_services;

import com.muvit.MUVIT.api.dto.request.ServiceRequest;
import com.muvit.MUVIT.api.dto.response.ServiceResponse;
import com.muvit.MUVIT.infrastructure.services.interfaces.CRUDService;

public interface IServService extends CRUDService<ServiceRequest, ServiceResponse, String>{

    public ServiceResponse getById(String id);
}
