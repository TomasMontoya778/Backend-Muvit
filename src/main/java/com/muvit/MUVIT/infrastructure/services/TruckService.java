package com.muvit.MUVIT.infrastructure.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.muvit.MUVIT.api.dto.request.TruckRequest;
import com.muvit.MUVIT.api.dto.response.TruckResponse;
import com.muvit.MUVIT.infrastructure.abstract_services.ITruckService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class TruckService implements ITruckService{@Override
    public TruckResponse create(TruckRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public TruckResponse update(String id, TruckRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<TruckResponse> getAll(int page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public TruckResponse getById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

}
