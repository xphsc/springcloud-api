package com.xphsc.api.user.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
@Entity
@Table(name="T_USER")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String uname;
    private String age;
    private String password;


    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}

