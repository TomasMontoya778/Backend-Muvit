package com.muvit.MUVIT.api.dto.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder // Patron de diseño para creacion de clases
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private RolResponse rol;
}
