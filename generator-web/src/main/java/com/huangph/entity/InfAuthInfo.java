package com.huangph.entity;
import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
*  第三方接口鉴权表
* @author huangph 2018-11-06
*/
@Data
public class InfAuthInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * auth_info_id
    */
    private int authInfoId;

    /**
    * 接入系统名称
    */
    private String systemName;

    /**
    * 接入系统code
    */
    private String systemCode;

    /**
    * 接入系统类型 0-内部系统 1-外部系统
    */
    private int systemType;

    /**
    * 接口访问key
    */
    private String apiKey;

    /**
    * 接口访问秘钥
    */
    private String apiSecret;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 失效时间
    */
    private Date expireDate;

    /**
    * 状态 0-失效 1-有效
    */
    private int status;

    /**
    * 状态变更时间
    */
    private Date statusTime;

    /**
    * 备注
    */
    private String remark;


}