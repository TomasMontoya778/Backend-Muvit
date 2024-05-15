package com.muvit.MUVIT.infrastructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.muvit.MUVIT.api.dto.request.TruckRequest;
import com.muvit.MUVIT.api.dto.response.DriverResponse;
import com.muvit.MUVIT.api.dto.response.DriverToTruckResponse;
import com.muvit.MUVIT.api.dto.response.TruckResponse;
import com.muvit.MUVIT.domain.entities.Driver;
import com.muvit.MUVIT.domain.entities.Truck;
import com.muvit.MUVIT.domain.repositories.DriverRepository;
import com.muvit.MUVIT.domain.repositories.TruckRepository;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.ITruckService;
import com.muvit.MUVIT.util.exceptions.BadRequestException;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TruckService implements ITruckService{

    @Autowired
    private final TruckRepository objTruckRepository;
    @Autowired
    private final DriverRepository objDriverRepository;
    @Override
    public TruckResponse getById(String id) {
        return this.entityToResponse(this.find(id));
    }
    private Truck find(String id){
        return this.objTruckRepository.findById(id).orElseThrow(()-> new BadRequestException("No hay registros con el id suministrado"));
    }
    @Override
    public TruckResponse create(TruckRequest request) {
        Truck truck = this.requestToEntity(request, new Truck());
        return this.entityToResponse(this.objTruckRepository.save(truck));
    }

    private TruckResponse entityToResponse(Truck truck){
        TruckResponse truckResponse = new TruckResponse();
        DriverToTruckResponse driverResponse = new DriverToTruckResponse();
        BeanUtils.copyProperties(truck, truckResponse);
        BeanUtils.copyProperties(truck.getId_driver_truck(), driverResponse);
        truckResponse.setId_driver(driverResponse);
        return truckResponse;
    }

    private Truck requestToEntity(TruckRequest truckRequest, Truck truck){
        Driver findDriver = this.objDriverRepository.findById(truckRequest.getId_driver())
        .orElseThrow(()-> new BadRequestException("No hay contenido disponible con el ID suministrado"));
        truck.setModel(truckRequest.getModel());
        truck.setSoat(truckRequest.getSoat());
        truck.setTecnomecanica(truckRequest.getTecnomecanica());
        truck.setBody(truckRequest.getBody());
        truck.setId_driver_truck(findDriver);
        return truck;
    }

    @Override
    public void delete(String id) {
        Truck truck = this.find(id);

        this.objTruckRepository.delete(truck);
    }

    @Override
    public Page<TruckResponse> getAll(int page, int size) {
        if(page < 0) page = 0;
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.objTruckRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    public TruckResponse update(String id, TruckRequest request) {
        Truck truck = this.find(id);
        Truck truckToUpdate = this.requestToEntity(request, truck);
        return this.entityToResponse(this.objTruckRepository.save(truckToUpdate));
    }
}
