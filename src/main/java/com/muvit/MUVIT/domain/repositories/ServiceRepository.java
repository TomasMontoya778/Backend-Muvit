package com.muvit.MUVIT.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muvit.MUVIT.domain.entities.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, String>{

}
