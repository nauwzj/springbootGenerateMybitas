package com.huangph.dao;

import com.huangph.entity.Userinfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* 用户信息
* @author huangph 2018-10-26
*/
@Component
public interface UserinfoDao {

    /**
    * 新增
    */
    public int insert(@Param("userinfo") Userinfo userinfo);

    /**
    * 删除
    */
    public int delete(@Param("id") int id);

    /**
    * 更新
    */
    public int update(@Param("userinfo") Userinfo userinfo);

    /**
    * Load查询
    */
    public Userinfo load(@Param("id") int id);

    /**
    * 分页查询Data
    */
	public List<Userinfo> pageList(@Param("offset") int offset,
                                                 @Param("pagesize") int pagesize);

    /**
    * 分页查询Count
    */
    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize);

}
