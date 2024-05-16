package com.muvit.MUVIT.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.muvit.MUVIT.domain.entities.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, String> {
    @Query(value = "SELECT s FROM ServiceEntity s JOIN FETCH s.id_user u WHERE u.id = :idUsuario AND s.statusService = 'ACTIVE'")
    public ServiceEntity getByUserId(String idUsuario);
}
