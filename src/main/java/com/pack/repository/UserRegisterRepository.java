package com.pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.entity.UserRegisterEntity;

@Repository
public interface UserRegisterRepository extends JpaRepository<UserRegisterEntity, Integer> {

}
