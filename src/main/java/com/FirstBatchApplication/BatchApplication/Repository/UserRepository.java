package com.FirstBatchApplication.BatchApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FirstBatchApplication.BatchApplication.Model.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}
