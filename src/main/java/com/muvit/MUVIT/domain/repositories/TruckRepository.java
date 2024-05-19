package com.muvit.MUVIT.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muvit.MUVIT.domain.entities.Truck;

public interface TruckRepository extends JpaRepository<Truck, String>{

}
