package com.muvit.MUVIT.infrastructure.services;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.muvit.MUVIT.api.dto.request.ServiceRequest;
import com.muvit.MUVIT.api.dto.response.BasicRolResponse;
import com.muvit.MUVIT.api.dto.response.DriverResponse;
import com.muvit.MUVIT.api.dto.response.RolResponse;
import com.muvit.MUVIT.api.dto.response.ServiceResponse;
import com.muvit.MUVIT.api.dto.response.UserToServiceResponse;
import com.muvit.MUVIT.domain.entities.Driver;
import com.muvit.MUVIT.domain.entities.Rol;
import com.muvit.MUVIT.domain.entities.ServiceEntity;
import com.muvit.MUVIT.domain.entities.User;
import com.muvit.MUVIT.domain.repositories.DriverRepository;
import com.muvit.MUVIT.domain.repositories.ServiceRepository;
import com.muvit.MUVIT.domain.repositories.UserRepository;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IServiceService;
import com.muvit.MUVIT.util.enums.StateServiceEnum;
import com.muvit.MUVIT.util.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceService implements IServiceService {
    @Autowired
    private final ServiceRepository objServiceRepository;
    @Autowired
    private final UserRepository objUserRepository;
    @Autowired
    private final DriverRepository objDriverRepository;

    @Override
    public ServiceResponse getById(String id) {
        return this.entityToResponse(this.find(id));
    }

    private ServiceEntity find(String id) {
        return this.objServiceRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("No se encontró el ID del servicio"));
    }

    @Override
    public ServiceResponse create(ServiceRequest request) {
        ServiceEntity objService = this.requestToEntity(request, new ServiceEntity());
        if (objService.getAssistant() > 6) {
            BadRequestException error = new BadRequestException("Our assistant limits is SIX.");
            throw error;
        }
        return this.entityToResponse(this.objServiceRepository.save(objService));
    }

    private ServiceResponse entityToResponse(ServiceEntity serviceEntity) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setId_service(serviceEntity.getId_service());
        serviceResponse.setTypeService(serviceEntity.getTypeService());
        serviceResponse.setAssistant(serviceEntity.getAssistant());
        serviceResponse.setPrice(serviceEntity.getPrice());
        serviceResponse.setDate(serviceEntity.getDate());
        serviceResponse.setDistance(serviceEntity.getDistance());
        serviceResponse.setFinalPoint(serviceEntity.getFinalPoint());
        serviceResponse.setStartPoint(serviceEntity.getStartPoint());
        serviceResponse.setTime(serviceEntity.getTime());
        serviceResponse.setStatusService(serviceEntity.getStatusService());
        UserToServiceResponse userResponse = new UserToServiceResponse();
        BeanUtils.copyProperties(serviceEntity.getId_user(), userResponse);
        BasicRolResponse rolBasic = this.rolToBasicRolResponse(serviceEntity.getId_user().getRol());
        DriverResponse driverResponse = new DriverResponse();
        BeanUtils.copyProperties(serviceEntity.getId_driver(), driverResponse);
        userResponse.setRol(rolBasic);
        serviceResponse.setUser(userResponse);
        serviceResponse.setDriver(driverResponse);
        return serviceResponse;
    }

    private BasicRolResponse rolToBasicRolResponse(Rol rol) {
        BasicRolResponse basicResponse = new BasicRolResponse();
        BeanUtils.copyProperties(rol, basicResponse);
        return basicResponse;
    }

    private ServiceEntity requestToEntity(ServiceRequest serviceRequest, ServiceEntity objService) {
        User user = this.objUserRepository.findById(serviceRequest.getUser())
                .orElseThrow(() -> new BadRequestException("It doesn't found any id."));
        Driver driver = this.objDriverRepository.findById(serviceRequest.getDriver())
                .orElseThrow(() -> new BadRequestException("It doesn't found any id"));
        objService.setTypeService(serviceRequest.getTypeService());
        objService.setDistance(serviceRequest.getDistance());
        objService.setAssistant(serviceRequest.getAssistant());
        objService.setPrice(serviceRequest.getPrice());
        objService.setStartPoint(serviceRequest.getStartPoint());
        objService.setFinalPoint(serviceRequest.getFinalPoint());
        objService.setDate(serviceRequest.getDate());
        objService.setTime(serviceRequest.getTime());
        String status = serviceRequest.getStatusService();
        objService.setStatusService(StateServiceEnum.valueOf(status));
        objService.setId_driver(driver);
        objService.setId_user(user);
        System.out.println(objService);
        return objService;
    }

    @Override
    public void delete(String id) {
        this.objServiceRepository.deleteById(id);
    }

    @Override
    public Page<ServiceResponse> getAll(int page, int size) {
        if (page < 0)
            page = 0;
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.objServiceRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    public ServiceResponse update(String id, ServiceRequest request) {
        ServiceEntity objServiceEntity = this.find(id);
        ServiceEntity objServiceUpdate = this.requestToEntity(request, objServiceEntity);
        return this.entityToResponse(this.objServiceRepository.save(objServiceUpdate));
    }

    @Override
    public Optional<ServiceResponse> getActiveServiceByUserId(String userId) {
        Optional<ServiceEntity> service = objServiceRepository.findActiveServiceByUserId(userId);
        return service.map(this::entityToResponse);
    }

    private User findUser(String id) {
        return this.objUserRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("No se encontró el ID del usuario"));
    }

}
