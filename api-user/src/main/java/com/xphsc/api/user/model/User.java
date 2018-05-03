package com.xphsc.api.user.model;

import com.querydsl.core.annotations.QueryExclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
@Entity
@Table(name="T_USER")
@Data
@QueryExclude
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uname;
    private String age;
    private String password;





}

