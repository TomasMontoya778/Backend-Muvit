package com.muvit.MUVIT.infrastructure.abstract_services.interfaces;

import com.muvit.MUVIT.api.dto.request.DriverRequest;
import com.muvit.MUVIT.api.dto.response.DriverResponse;

public interface IDriverService extends CrudService <DriverRequest,DriverResponse,String >{
    public DriverResponse getById(String id);
}
