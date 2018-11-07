package com.huangph.service;

import java.util.Map;

/**
* 第三方接口鉴权表
* @author huangph 2018-11-06
*/
public interface InfAuthInfoService {

    /**
    * infAuthInfo
    * 新增
    */
    ReturnT<String> insert(InfAuthInfo infAuthInfo);

    /**
    * infAuthInfo
    * 删除
    */
    ReturnT<String> delete(int id);

    /**
    * infAuthInfo
    * 更新
    */
    ReturnT<String> update(InfAuthInfo infAuthInfo);

    /**
    * Load查询
    */
    InfAuthInfo load(int id);

    /**

    * 分页查询
    */
    Map<String,Object> pageList(int offset, int pagesize);

}
