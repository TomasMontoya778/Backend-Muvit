package com.muvit.MUVIT.api.dto.request;

import com.muvit.MUVIT.util.enums.RolEnum;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolRequest {
private String nameUser;
private String password;
private RolEnum rol;
}
