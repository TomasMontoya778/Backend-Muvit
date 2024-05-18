package com.muvit.MUVIT.infrastructure.abstract_services.interfaces;

import com.muvit.MUVIT.api.dto.request.LoginRolRequest;
import com.muvit.MUVIT.api.dto.request.RegisterDriverReq;
import com.muvit.MUVIT.api.dto.request.RegisterReq;
import com.muvit.MUVIT.api.dto.request.RegisterUserReq;
import com.muvit.MUVIT.api.dto.response.AuthResp;

public interface IAuthService {
    public AuthResp login(LoginRolRequest request);
    public AuthResp register(RegisterReq request);
    public AuthResp registerDriver(RegisterDriverReq request);
    public AuthResp registerUser(RegisterUserReq request);
}
