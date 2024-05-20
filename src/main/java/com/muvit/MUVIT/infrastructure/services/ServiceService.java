package com.muvit.MUVIT.infrastructure.services;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.muvit.MUVIT.api.dto.request.ServiceRequest;
import com.muvit.MUVIT.api.dto.response.BasicRolResponse;
import com.muvit.MUVIT.api.dto.response.DriverBasicResponse;
import com.muvit.MUVIT.api.dto.response.DriverResponse;
import com.muvit.MUVIT.api.dto.response.RolResponse;
import com.muvit.MUVIT.api.dto.response.ServiceResponse;
import com.muvit.MUVIT.api.dto.response.TruckDriverResponse;
import com.muvit.MUVIT.api.dto.response.UserResponse;
import com.muvit.MUVIT.api.dto.response.UserToServiceResponse;
import com.muvit.MUVIT.domain.entities.Driver;
import com.muvit.MUVIT.domain.entities.Rol;
import com.muvit.MUVIT.domain.entities.ServiceEntity;
import com.muvit.MUVIT.domain.entities.Truck;
import com.muvit.MUVIT.domain.entities.User;
import com.muvit.MUVIT.domain.repositories.DriverRepository;
import com.muvit.MUVIT.domain.repositories.ServiceRepository;
import com.muvit.MUVIT.domain.repositories.UserRepository;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IServiceService;
import com.muvit.MUVIT.util.enums.BodyEnum;
import com.muvit.MUVIT.util.enums.PaymentMethods;
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
        serviceResponse.setPaymentMethod(serviceEntity.getPayment());
        serviceResponse.setStatusService(serviceEntity.getStatusService());
        UserToServiceResponse userResponse = new UserToServiceResponse();
        BeanUtils.copyProperties(serviceEntity.getId_user(), userResponse);
        BasicRolResponse rolBasic = this.rolToBasicRolResponse(serviceEntity.getId_user().getRol());
        BasicRolResponse rolBasicDriver = this.rolToBasicRolResponse(serviceEntity.getId_driver().getRol());
        DriverBasicResponse driverResponse = new DriverBasicResponse();
        List<TruckDriverResponse> truckList = new ArrayList<>();
        Driver driver = serviceEntity.getId_driver();
        if (driver.getTruck() != null) {
            for (Truck truck : driver.getTruck()) {
                TruckDriverResponse truckResponse = entityToTruckDriverResponse(truck);
                truckResponse.setId(truck.getId());
                truckResponse.setBody(truck.getBody());
                truckResponse.setModel(truck.getModel());
                truckResponse.setSoat(truck.getSoat());
                truckResponse.setTecnomecanica(truck.getTecnomecanica());
                truckResponse.setLicensePlate(truck.getLicensePlate());
                truckList.add(truckResponse);
            }
        }
        driverResponse.setTruck(truckList);
        BeanUtils.copyProperties(driver, driverResponse);
        BeanUtils.copyProperties(serviceEntity.getId_driver(), driverResponse);
        userResponse.setRol(rolBasic);
        driverResponse.setRol(rolBasicDriver);
        serviceResponse.setUser(userResponse);
        serviceResponse.setDriver(driverResponse);
        serviceResponse.setSize(serviceEntity.getSize());
        return serviceResponse;
    }

    private BasicRolResponse rolToBasicRolResponse(Rol rol) {
        BasicRolResponse basicResponse = new BasicRolResponse();
        BeanUtils.copyProperties(rol, basicResponse);
        return basicResponse;
    }

    private TruckDriverResponse entityToTruckDriverResponse(Truck truck) {
        TruckDriverResponse listTruckDriverResponse = new TruckDriverResponse();
        listTruckDriverResponse.setId(truck.getId());
        listTruckDriverResponse.setModel(truck.getModel());
        listTruckDriverResponse.setBody(truck.getBody());
        listTruckDriverResponse.setSoat(truck.getSoat());
        listTruckDriverResponse.setTecnomecanica(truck.getTecnomecanica());
        return listTruckDriverResponse;
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
        if (serviceRequest.getSize() != null) {
            String size = serviceRequest.getSize();
            objService.setSize(BodyEnum.valueOf(size));
        }
        if (serviceRequest.getPaymentMethod() != null) {
            String payment = serviceRequest.getPaymentMethod();
            objService.setPayment(PaymentMethods.valueOf(payment));
        }
        if (serviceRequest.getStatusService() != null) {
            String status = serviceRequest.getStatusService();
            objService.setStatusService(StateServiceEnum.valueOf(status));
        }
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
    public Page<ServiceResponse> getInactiveServiceByUserId(String userId, Pageable pageable) {
        Page<ServiceEntity> userService = this.objServiceRepository.findInactiveServiceByUserId(userId, pageable);
        return userService.map(this::entityToResponse);
    }

    @Override
    public Optional<ServiceResponse> getActiveServiceByUserId(String userId) {
        Optional<ServiceEntity> service = objServiceRepository.findActiveServiceByUserId(userId);
        return service.map(this::entityToResponse);
    }

    @Override
    public Page<ServiceResponse> getAllActiveService(Pageable pageable) {
        Page<ServiceEntity> service = objServiceRepository.getAllActiveService(pageable);
        return service.map(this::entityToResponse);
    }

    @Override
    public Page<ServiceResponse> getInactiveServiceByDriverId(String driverId, Pageable pageable) {
        Page<ServiceEntity> driverServices = this.objServiceRepository.findInactiveServiceByDriverId(driverId,
                pageable);
        return driverServices.map(this::entityToResponse);
    }

}
