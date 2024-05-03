package com.muvit.MUVIT.infrastructure.abstract_services;

import com.muvit.MUVIT.api.dto.request.TruckRequest;
import com.muvit.MUVIT.api.dto.response.TruckResponse;
import com.muvit.MUVIT.infrastructure.services.interfaces.CRUDService;

public interface ITruckService extends CRUDService<TruckRequest, TruckResponse, String>{
        public TruckResponse getById(String id);
}
