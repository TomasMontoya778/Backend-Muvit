package com.muvit.MUVIT.infrastructure.abstract_services;

import com.muvit.MUVIT.api.dto.request.DriverRequest;
import com.muvit.MUVIT.api.dto.response.DriverResponse;
import com.muvit.MUVIT.infrastructure.services.interfaces.CRUDService;

public interface IDriverService extends CRUDService<DriverRequest, DriverResponse, String>{

    public DriverResponse getById(String id);

}
