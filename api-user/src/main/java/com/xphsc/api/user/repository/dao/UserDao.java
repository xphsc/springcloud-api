package com.xphsc.api.user.repository.dao;


import com.xphsc.api.frame.base.BaseRepository;
import com.xphsc.api.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.io.Serializable;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */

public interface UserDao extends BaseRepository<User,Serializable>,QueryDslPredicateExecutor<User> {
}
