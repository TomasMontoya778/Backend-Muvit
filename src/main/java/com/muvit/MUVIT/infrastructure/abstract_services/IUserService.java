package com.muvit.MUVIT.infrastructure.abstract_services;

import com.muvit.MUVIT.api.dto.request.UserRequest;
import com.muvit.MUVIT.api.dto.response.UserResponse;
import com.muvit.MUVIT.infrastructure.services.interfaces.CRUDService;

public interface IUserService extends CRUDService<UserRequest, UserResponse, String>{
    public UserRequest getById(String id);
}
