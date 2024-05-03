package com.muvit.MUVIT.infrastructure.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.muvit.MUVIT.api.dto.request.RolRequest;
import com.muvit.MUVIT.api.dto.response.RolResponse;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IRolService;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class RolService implements IRolService{@Override
    public RolResponse getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RolResponse create(RolRequest request) {
        
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Page<RolResponse> getAll(int page, int size) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RolResponse update(Long id, RolRequest request) {
        // TODO Auto-generated method stub
        return null;
    }
}
