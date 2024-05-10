package com.muvit.MUVIT.infrastructure.abstract_services.interfaces;

import com.muvit.MUVIT.api.dto.request.RolRequest;
import com.muvit.MUVIT.api.dto.response.RolResponse;

public interface IRolService extends CrudService<RolRequest, RolResponse, Long> {
        public RolResponse getById(Long id);

        // @Query(value = "SELECT ")
        // public RolResponse fieldByRol =
}
