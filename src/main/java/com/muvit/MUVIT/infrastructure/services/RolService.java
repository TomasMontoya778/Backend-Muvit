package com.muvit.MUVIT.infrastructure.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.muvit.MUVIT.api.dto.request.RolRequest;
import com.muvit.MUVIT.api.dto.response.RolResponse;
import com.muvit.MUVIT.infrastructure.abstract_services.IRolService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RolService implements IRolService{

    @Override
    public RolResponse create(RolRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public RolResponse update(String id, RolRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<RolResponse> getAll(int page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public RolResponse getById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

}
