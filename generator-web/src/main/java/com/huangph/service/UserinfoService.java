package com.huangph.service;

import com.huangph.entity.Userinfo;
import com.softdev.system.generator.entity.ReturnT;

import java.util.Map;

/**
* 用户信息
* @author huangph 2018-10-26
*/
public interface UserinfoService {

    /**
    * 新增
    */
    ReturnT<String> insert(Userinfo userinfo);

    /**
    * 删除
    */
    ReturnT<String> delete(int id);

    /**
     *
     * @param userinfo
     * @return
     */
    ReturnT<String> update(Userinfo userinfo);

    /**
    * Load查询
    */
    Userinfo load(int id);

    /**
    * 分页查询
    */
    Map<String,Object> pageList(int offset, int pagesize);

}
