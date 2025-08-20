package com.pavan.userInfo.repo;

import com.pavan.userInfo.enitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

}
