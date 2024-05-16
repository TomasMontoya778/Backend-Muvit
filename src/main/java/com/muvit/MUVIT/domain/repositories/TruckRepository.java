package com.muvit.MUVIT.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.muvit.MUVIT.domain.entities.Truck;

@Repository
public interface TruckRepository extends JpaRepository<Truck, String>{

}
