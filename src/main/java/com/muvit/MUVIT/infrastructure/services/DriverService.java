package com.muvit.MUVIT.infrastructure.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.muvit.MUVIT.api.dto.request.DriverRequest;
import com.muvit.MUVIT.api.dto.response.DriverResponse;
import com.muvit.MUVIT.api.dto.response.RolResponse;
import com.muvit.MUVIT.api.dto.response.TruckDriverResponse;
import com.muvit.MUVIT.domain.entities.Driver;
import com.muvit.MUVIT.domain.entities.Rol;
import com.muvit.MUVIT.domain.entities.Truck;
import com.muvit.MUVIT.domain.repositories.DriverRepository;
import com.muvit.MUVIT.domain.repositories.RolRepository;
import com.muvit.MUVIT.domain.repositories.TruckRepository;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IDriverService;
import com.muvit.MUVIT.util.enums.DNITypeEnum;
import com.muvit.MUVIT.util.exceptions.BadRequestException;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DriverService implements IDriverService{
    @Autowired
    private final DriverRepository objDriverRepository;
    @Autowired
    private final RolRepository objRolRepository;
    @Autowired
    private final TruckRepository objTruckRepository;

    @Override
    public DriverResponse getById(String id) {
        return this.entityToDriverResponse(this.find(id));
    }

    @Override
    public DriverResponse create(DriverRequest request) {
       
        Driver driver = this.RequestToEntity(request, new Driver());

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

        Rol objRol = this.objRolRepository.findById(request.getRol()).orElseThrow();
       driver = this.RequestToEntity(request, driver);

       driver.setRol(objRol);


        return this.entityToDriverResponse(this.objDriverRepository.save(driver));
    }


    private Driver find(String id){

        return this.objDriverRepository.findById(id).orElseThrow(()-> new BadRequestException("No hay registros con el ID suministrado"));
    }

    private DriverResponse entityToDriverResponse(Driver objDriver){
        DriverResponse response = new DriverResponse();
        RolResponse rol = new RolResponse();
        List<TruckDriverResponse> truck = new ArrayList<>();
        BeanUtils.copyProperties(objDriver.getTruck(), truck);
        BeanUtils.copyProperties(objDriver.getRol(), rol);
        BeanUtils.copyProperties(objDriver, response);
        response.setTruck(truck);
        response.setRol(rol);
        return response;
    }
    private TruckDriverResponse entityToTruckDriverResponse(Truck listTruck){
        TruckDriverResponse listTruckDriverResponse = new TruckDriverResponse();
        listTruck.setId(listTruckDriverResponse.getId());
        listTruck.setModel(listTruckDriverResponse.getModel());
        listTruck.setBody(listTruckDriverResponse.getBody());
        listTruck.setSoat(listTruckDriverResponse.getSoat());
        listTruck.setTecnomecanica(listTruckDriverResponse.getTecnomecanica());
        return listTruckDriverResponse;
    }
    private Driver RequestToEntity(DriverRequest request, Driver objDriver){
        Rol rol = this.objRolRepository.findById(request.getRol())
                .orElseThrow(() -> new BadRequestException("No hay contenido con el ID suministrado"));
        objDriver.setName(request.getName());
        objDriver.setLastName(request.getLastName());
        objDriver.setEmail(request.getEmail());
        objDriver.setDNI_type(DNITypeEnum.valueOf(request.getDNI_type()));
        objDriver.setDNI(request.getDNI());
        objDriver.setPhoneNumber(request.getPhoneNumber());
        objDriver.setRol(rol);
        return objDriver;
    }
}
