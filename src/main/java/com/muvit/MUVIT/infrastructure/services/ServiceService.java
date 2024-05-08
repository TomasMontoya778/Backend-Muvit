package com.muvit.MUVIT.infrastructure.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.muvit.MUVIT.api.dto.request.ServiceRequest;
import com.muvit.MUVIT.api.dto.response.ServiceResponse;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IServiceService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceService implements IServiceService{

    @Override
    public ServiceResponse getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceResponse create(ServiceRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Page<ServiceResponse> getAll(int page, int size) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceResponse update(String id, ServiceRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

}
