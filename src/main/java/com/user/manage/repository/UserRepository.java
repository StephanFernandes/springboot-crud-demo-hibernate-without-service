package com.user.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.manage.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
