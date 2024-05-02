package com.muvit.MUVIT.infrastructure.abstract_services.interfaces;

import com.muvit.MUVIT.api.dto.request.TruckRequest;
import com.muvit.MUVIT.api.dto.response.TruckResponse;

public interface ITruckService extends CrudService<TruckRequest,TruckResponse,String>{
    public TruckResponse getById(String id);
}
