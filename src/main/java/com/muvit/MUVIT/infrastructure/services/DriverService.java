package com.muvit.MUVIT.infrastructure.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.muvit.MUVIT.api.dto.request.DriverRequest;
import com.muvit.MUVIT.api.dto.response.DriverResponse;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IDriverService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DriverService implements IDriverService{

    @Override
    public DriverResponse getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DriverResponse create(DriverRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Page<DriverResponse> getAll(int page, int size) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DriverResponse update(String id, DriverRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

}
