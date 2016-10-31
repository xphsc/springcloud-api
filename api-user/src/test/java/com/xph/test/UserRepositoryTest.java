package com.xph.test;


import com.xph.api.user.Application;
import com.xph.api.user.entity.User;
import com.xph.api.user.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by ${huipei.x} on 2016/10/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void findALL(){
        List<User> userList= userRepository.findAll();
        for(User user:userList){
            System.out.println("111"+user.getUname()  );
        }
        ;
    }
}
