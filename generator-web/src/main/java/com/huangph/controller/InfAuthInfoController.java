package com.huangph.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
* 第三方接口鉴权表
* @author huangph 2018-11-06
*/
@Controller
public class InfAuthInfoController {

    @Resource
    private InfAuthInfoService infAuthInfoService;

    /**
    * 新增
    */
    @RequestMapping("/insert")
    @ResponseBody
    public ReturnT<String> insert(InfAuthInfo infAuthInfo){
        return infAuthInfoService.insert(infAuthInfo);
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    @ResponseBody
    public ReturnT<String> delete(int id){
        return infAuthInfoService.delete(id);
    }

    /**
    * 更新
    */
    @RequestMapping("/update")
    @ResponseBody
    public ReturnT<String> update(InfAuthInfo infAuthInfo){
        return infAuthInfoService.update(infAuthInfo);
    }

    /**
    * Load查询
    */
    @RequestMapping("/load")
    @ResponseBody
    public InfAuthInfo load(int id){
        return infAuthInfoService.load(id);
    }

    /**
    * 分页查询
    */
    @RequestMapping("/pageList")
    @ResponseBody
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return infAuthInfoService.pageList(offset, pagesize);
    }

}
