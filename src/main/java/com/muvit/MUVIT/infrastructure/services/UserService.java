package com.muvit.MUVIT.infrastructure.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.muvit.MUVIT.api.dto.request.UserRequest;
import com.muvit.MUVIT.api.dto.response.PaymentToUserResponse;
import com.muvit.MUVIT.api.dto.response.RolResponse;
import com.muvit.MUVIT.api.dto.response.ServiceToUserResponse;
import com.muvit.MUVIT.api.dto.response.TruckDriverResponse;
import com.muvit.MUVIT.api.dto.response.UserResponse;
import com.muvit.MUVIT.api.dto.response.UserToServiceResponse;
import com.muvit.MUVIT.domain.entities.Payment;
import com.muvit.MUVIT.domain.entities.Rol;
import com.muvit.MUVIT.domain.entities.ServiceEntity;
import com.muvit.MUVIT.domain.entities.Truck;
import com.muvit.MUVIT.domain.entities.User;
import com.muvit.MUVIT.domain.repositories.RolRepository;
import com.muvit.MUVIT.domain.repositories.UserRepository;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IUserService;
import com.muvit.MUVIT.util.enums.PaymentMethods;
import com.muvit.MUVIT.util.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private final UserRepository objUserRepository;
    @Autowired
    private final RolRepository objRolRepository;

    @Override
    public UserResponse getById(String id) {
        return this.entityToResponse(this.find(id));
    }

    private User find(String id) {
        return this.objUserRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("No hay registros con el ID suministrado"));
    }

    @Override
    public UserResponse create(UserRequest request) {
        User objUser = this.requestToEntity(request, new User());

        return this.entityToResponse(this.objUserRepository.save(objUser));
    }

    private UserResponse entityToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        RolResponse rol = new RolResponse();
        List<ServiceToUserResponse> serviceList = new ArrayList<>();
        List<PaymentToUserResponse> paymentList = new ArrayList<>();
        if (user.getUserService() != null) {
            for (ServiceEntity service : user.getUserService()) {
                ServiceToUserResponse serviceResponse = entityToServiceToUserResponse(service);
                BeanUtils.copyProperties(service, serviceResponse);
                serviceList.add(serviceResponse);
            }
        }
        if (user.getPaymentMethods() != null) {
            for (Payment payment : user.getPaymentMethods()) {
                PaymentToUserResponse paymentResponse = entityToPaymentToUserResponse(payment);
                BeanUtils.copyProperties(payment, paymentResponse);
                paymentList.add(paymentResponse);
            }
        }
        BeanUtils.copyProperties(user.getRol(), rol);
        BeanUtils.copyProperties(user, userResponse);
        userResponse.setService(serviceList);
        userResponse.setPaymentMethods(paymentList);
        userResponse.setRol(rol);
        return userResponse;
    }

    private ServiceToUserResponse entityToServiceToUserResponse(ServiceEntity service) {
        ServiceToUserResponse listServiceToUserResponse = new ServiceToUserResponse();
        BeanUtils.copyProperties(service, listServiceToUserResponse);
        return listServiceToUserResponse;
    }

    private PaymentToUserResponse entityToPaymentToUserResponse(Payment payment) {
        PaymentToUserResponse listPaymentToUserResponse = new PaymentToUserResponse();
        BeanUtils.copyProperties(payment, listPaymentToUserResponse);
        return listPaymentToUserResponse;
    }

    private User requestToEntity(UserRequest userRequest, User user) {
        Rol rol = this.objRolRepository.findById(userRequest.getRol())
                .orElseThrow(() -> new BadRequestException("No hay contenido disponible con el ID suministrado"));
        user.setName(userRequest.getName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setRol(rol);
        System.out.println(user);
        if (user.getUserService() == null) {
            user.setUserService(new ArrayList<>());
        }
        return user;
    }

    @Override
    public void delete(String id) {
        User user = this.find(id);

        this.objUserRepository.delete(user);
    }

    @Override
    public Page<UserResponse> getAll(int page, int size) {
        if (page < 0)
            page = 0;
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.objUserRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    public UserResponse update(String id, UserRequest request) {
        User objUser = this.find(id);
        User objUserUpdate = this.requestToEntity(request, objUser);
        return this.entityToResponse(this.objUserRepository.save(objUserUpdate));
    }

}
