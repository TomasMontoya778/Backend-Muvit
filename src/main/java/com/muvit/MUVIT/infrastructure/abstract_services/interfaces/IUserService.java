package com.muvit.MUVIT.infrastructure.abstract_services.interfaces;

import com.muvit.MUVIT.api.dto.request.UserRequest;
import com.muvit.MUVIT.api.dto.response.UserResponse;

public interface IUserService extends CrudService<UserRequest,UserResponse,String>{
        public UserResponse getById(String id);
        public UserResponse getByEmail(String email);
}