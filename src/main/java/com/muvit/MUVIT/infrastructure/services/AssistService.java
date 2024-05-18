package com.muvit.MUVIT.infrastructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.muvit.MUVIT.api.dto.request.AssistReq;
import com.muvit.MUVIT.api.dto.response.AssistRes;
import com.muvit.MUVIT.api.dto.response.AssistToDriverResp;
import com.muvit.MUVIT.domain.entities.Assistant;
import com.muvit.MUVIT.domain.entities.Driver;
import com.muvit.MUVIT.domain.entities.Rol;
import com.muvit.MUVIT.domain.repositories.AssistRepository;
import com.muvit.MUVIT.domain.repositories.DriverRepository;
import com.muvit.MUVIT.domain.repositories.RolRepository;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IAssisService;
import com.muvit.MUVIT.util.enums.DNITypeEnum;
import com.muvit.MUVIT.util.enums.RolEnum;
import com.muvit.MUVIT.util.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AssistService implements IAssisService {

    private final AssistRepository objAssistRepository;
    private final DriverRepository objDriverRepository;
    private final RolRepository objRolRepository;

    @Override
    public void delete(String id) {
        Assistant assistant = this.find(id);

        objAssistRepository.delete(assistant);
    }

    @Override
    public AssistRes create(AssistReq request) {

        Assistant assistant = this.RequestToEntity(request, new Assistant());

        return this.entityToAssistRes(this.objAssistRepository.save(assistant));
    }

    @Override
    public AssistRes update(String id, AssistReq request) {
        Assistant assistant = this.find(id);

        Driver driver = this.objDriverRepository.findById(request.getDriver()).orElseThrow();

        assistant = this.RequestToEntity(request, assistant);

        assistant.setDriver(driver);

        return this.entityToAssistRes(this.objAssistRepository.save(assistant));
    }

    @Override
    public Page<AssistRes> getAll(int page, int size) {
        if (page < 0)
            page = 0;

        PageRequest objPageRequest = PageRequest.of(page, size);

        return this.objAssistRepository.findAll(objPageRequest).map(this::entityToAssistRes);
    }

    @Override
    public AssistRes getById(String id) {
        return this.entityToAssistRes(this.find(id));
    }

    private AssistRes entityToAssistRes(Assistant objAssistant) {
        AssistRes response = new AssistRes();
        AssistToDriverResp driverResponse = new AssistToDriverResp();

        BeanUtils.copyProperties(objAssistant.getDriver(), driverResponse);
        BeanUtils.copyProperties(objAssistant, response);
        response.setDriver(driverResponse);
        return response;
    }

    private Assistant RequestToEntity(AssistReq request, Assistant objAssistant) {

        Driver driver = this.objDriverRepository.findById(request.getDriver())
                .orElseThrow(() -> new BadRequestException("No hay contenido con el Id suministrado"));
    
            objAssistant.setName(request.getName());
            objAssistant.setLastName(request.getLastName());
            objAssistant.setDNI_type(DNITypeEnum.valueOf(request.getDNI_type()));
            objAssistant.setDNI(request.getDNI());

            Rol rol = driver.getRol();

            rol.setRolEnum(RolEnum.Driver);
            rol = objRolRepository.save(rol);
            driver.setRol(rol);

            objAssistant.setDriver(driver);
            return objAssistant;
        

    }

    private Assistant find(String id) {
        return this.objAssistRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("No hay registros con el ID suministrado"));
    }

}
