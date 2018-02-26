package com.xphsc.test;


import com.xphsc.api.user.UserApplication;
import com.xphsc.api.user.model.User;
import com.xphsc.api.user.repository.dao.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserApplication.class)
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
