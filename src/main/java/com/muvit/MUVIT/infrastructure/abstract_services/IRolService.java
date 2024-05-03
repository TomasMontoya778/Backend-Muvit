package com.muvit.MUVIT.infrastructure.abstract_services;

import com.muvit.MUVIT.api.dto.request.RolRequest;
import com.muvit.MUVIT.api.dto.response.RolResponse;
import com.muvit.MUVIT.infrastructure.services.interfaces.CRUDService;

public interface IRolService extends CRUDService<RolRequest, RolResponse, String>{

    public RolResponse getById(String id);
}
