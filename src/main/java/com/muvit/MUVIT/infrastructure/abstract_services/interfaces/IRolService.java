package com.muvit.MUVIT.infrastructure.abstract_services.interfaces;

import org.springframework.data.jpa.repository.Query;

import com.muvit.MUVIT.api.dto.request.RolRequest;
import com.muvit.MUVIT.api.dto.response.RolResponse;

public interface IRolService extends CrudService<RolRequest, RolResponse, Long> {
        public RolResponse getById(Long id);

        @Query(value = "select r from rol r where r.rol = 'Driver'")
        public RolResponse findByRol(String rol);

        


}
