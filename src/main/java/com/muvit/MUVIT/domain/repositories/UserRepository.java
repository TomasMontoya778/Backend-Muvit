package com.muvit.MUVIT.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muvit.MUVIT.domain.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
}
