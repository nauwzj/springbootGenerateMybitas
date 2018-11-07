package com.huangph.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* 第三方接口鉴权表
* @author huangph 2018-11-06
*/
@Component
public interface InfAuthInfoDao {

    /**
    * 新增
    */
    public int insert(@Param("infAuthInfo") InfAuthInfo infAuthInfo);

    /**
    * 删除
    */
    public int delete(@Param("id") int id);

    /**
    * 更新
    */
    public int update(@Param("infAuthInfo") InfAuthInfo infAuthInfo);

    /**
    * Load查询
    */
    public InfAuthInfo load(@Param("id") int id);

    /**
    * 分页查询Data
    */
	public List<InfAuthInfo> pageList(@Param("offset") int offset,
                                                 @Param("pagesize") int pagesize);

    /**
    * 分页查询Count
    */
    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize);

}
