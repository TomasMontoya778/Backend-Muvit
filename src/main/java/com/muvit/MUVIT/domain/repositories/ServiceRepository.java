package com.muvit.MUVIT.domain.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.muvit.MUVIT.domain.entities.ServiceEntity;


@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, String>{
    @Query(value = "select s from service s JOIN s.id_user u WHERE u.id = :userId AND s.statusService = 'ACTIVE'")
    Optional<ServiceEntity> findActiveServiceByUserId(@Param("userId") String userId);

    @Query(value = "select s from service s JOIN s.id_user u WHERE u.id = :userId AND s.statusService = 'INACTIVE'")
    Page<ServiceEntity> findInactiveServiceByUserId(@Param("userId") String userId, Pageable pageable);

    @Query(value = "select s from service s JOIN s.id_user u WHERE s.statusService = 'ACTIVE'")
    Page<ServiceEntity> getAllActiveService(Pageable pageable);

    @Query(value = "select s from service s JOIN s.id_driver d WHERE d.id_driver = :driverId AND s.statusService = 'INACTIVE'")
    Page<ServiceEntity> findInactiveServiceByDriverId(@Param("driverId") String driverId, Pageable pageable);
}
