package com.muvit.MUVIT.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muvit.MUVIT.domain.entities.Driver;

public interface DriverRepository extends JpaRepository<Driver, String>{

}
