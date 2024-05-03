package com.muvit.MUVIT.infrastructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.muvit.MUVIT.api.dto.request.DriverRequest;
import com.muvit.MUVIT.api.dto.response.DriverResponse;
import com.muvit.MUVIT.domain.entities.Driver;
import com.muvit.MUVIT.domain.repositories.DriverRepository;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IDriverService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DriverService implements IDriverService{

    private final DriverRepository objDriverRepository;

    @Override
    public DriverResponse getById(String id) {
        
        return this.entityToDriverResponse(this.find(id));
    }

    @Override
    public DriverResponse create(DriverRequest request) {
        Driver driver = this.entityToDriverRequest(request, new Driver());

        return this.entityToDriverResponse(this.objDriverRepository.save(driver));
    }

    @Override
    public void delete(String id) {
        Driver driver = this.find(id);

        this.objDriverRepository.delete(driver);
    }

    @Override
    public Page<DriverResponse> getAll(int page, int size) {
        if(page < 0)
        page = 0;

        PageRequest objPage = PageRequest.of(page, size);

        return this.objDriverRepository.findAll(objPage).map(this::entityToDriverResponse);
    }

    @Override
    public DriverResponse update(String id, DriverRequest request) {
        Driver driver = this.find(id);

        Driver driverUpdate = this.entityToDriverRequest(request, driver);

        return this.entityToDriverResponse(this.objDriverRepository.save(driverUpdate));
    }


    private Driver find(String id){

        return this.objDriverRepository.findById(id).orElseThrow();
    }

    private DriverResponse entityToDriverResponse(Driver objDriver){
        DriverResponse response = new DriverResponse();

        BeanUtils.copyProperties(objDriver, response);

        return response;
    }

    private Driver entityToDriverRequest(DriverRequest request, Driver objDriver){

        BeanUtils.copyProperties(request, objDriver);

        return objDriver;
    }
}
