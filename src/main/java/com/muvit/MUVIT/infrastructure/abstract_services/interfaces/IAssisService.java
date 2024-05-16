package com.muvit.MUVIT.infrastructure.abstract_services.interfaces;

import com.muvit.MUVIT.api.dto.request.AssistReq;
import com.muvit.MUVIT.api.dto.response.AssistRes;

public interface IAssisService extends CrudService<AssistReq, AssistRes, String>{
    public AssistRes getById(String id);
}
