package com.muvit.MUVIT.infrastructure.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.muvit.MUVIT.api.dto.request.UserRequest;
import com.muvit.MUVIT.api.dto.response.UserResponse;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IUserService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserService implements IUserService {@Override
    public UserResponse getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponse create(UserRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Page<UserResponse> getAll(int page, int size) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponse update(String id, UserRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

}
