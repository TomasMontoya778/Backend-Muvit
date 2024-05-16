package com.muvit.MUVIT.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.muvit.MUVIT.domain.entities.ServiceEntity;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, String> {
    // @Query(value = "SELECT s FROM service s JOIN FETCH s.id_user u WHERE u.id = userId")
    // ServiceEntity findServiceByUserId(String userId);
}
