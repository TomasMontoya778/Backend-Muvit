package com.muvit.MUVIT.domain.repositories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.muvit.MUVIT.domain.entities.ServiceEntity;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, String>{
    @Query(value = "select s from service s JOIN s.id_user u WHERE u.id = :userId AND s.statusService = 'ACTIVE'")
    Optional<ServiceEntity> findActiveServiceByUserId(@Param("userId") String userId);
}
