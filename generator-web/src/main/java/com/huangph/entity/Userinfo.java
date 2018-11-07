package com.huangph.entity;
import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
*  用户信息
* @author huangph 2018-10-26
*/
@Data
public class Userinfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 用户id
    */
    private int userId;

    /**
    * 用户名
    */
    private String username;

    /**
    * 创建时间
    */
    private Date addtime;


}