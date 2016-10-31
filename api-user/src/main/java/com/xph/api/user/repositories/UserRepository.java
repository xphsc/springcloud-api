package com.xph.api.user.repositories;


import com.xph.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ${huipei.x} on 2016/8/8.
 */

public interface UserRepository extends JpaRepository<User,Long> {
}
