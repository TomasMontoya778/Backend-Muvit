package com.muvit.MUVIT.infrastructure.services;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.muvit.MUVIT.api.dto.request.UserRequest;
import com.muvit.MUVIT.api.dto.response.UserResponse;
import com.muvit.MUVIT.domain.entities.User;
import com.muvit.MUVIT.domain.repositories.UserRepository;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IUserService;
import com.muvit.MUVIT.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private final UserRepository objUserRepository;
    @Override
    public UserResponse getById(String id) {
        return this.entityToResponse(this.find(id));
    }
    private User find(String id){
        return this.objUserRepository.findById(id).orElseThrow(()-> new IdNotFoundException("User"));
    }

    @Override
    public UserResponse create(UserRequest request) { 
        User objUser = this.userRequest(request, new User());
        return this.entityToResponse(this.objUserRepository.save(objUser));
    }

    private UserResponse entityToResponse(User user){
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }
    private User userRequest(UserRequest userRequest, User user){
        BeanUtils.copyProperties(userRequest, user);
        user.setUserService(new ArrayList<>());
        return user;
    }

    @Override
    public void delete(String id) {
        this.objUserRepository.deleteById(id);
    }

    @Override
    public Page<UserResponse> getAll(int page, int size) {
        if(page < 0) page = 0;
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.objUserRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    public UserResponse update(String id, UserRequest request) {
        User objUser = this.find(id);
        User objUserUpdate = this.userRequest(request, objUser);
        return this.entityToResponse(this.objUserRepository.save(objUserUpdate));
    }

}
