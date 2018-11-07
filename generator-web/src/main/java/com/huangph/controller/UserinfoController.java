package com.huangph.controller;

import com.huangph.entity.Userinfo;
import com.huangph.service.UserinfoService;
import com.softdev.system.generator.entity.ReturnT;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
* 用户信息
* @author huangph 2018-10-26
*/
@Controller
public class UserinfoController {

    @Resource
    private UserinfoService userinfoService;

    /**
    * 新增
    */
    @RequestMapping("/insert")
    @ResponseBody
    public ReturnT<String> insert(Userinfo userinfo){
        return userinfoService.insert(userinfo);
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    @ResponseBody
    public ReturnT<String> delete(int id){
        return userinfoService.delete(id);
    }

    /**
    * 更新
    */
    @RequestMapping("/update")
    @ResponseBody
    public ReturnT<String> update(Userinfo userinfo){
        return userinfoService.update(userinfo);
    }

    /**
    * Load查询
    */
    @RequestMapping("/load")
    @ResponseBody
    public Userinfo load(int id){
        return userinfoService.load(id);
    }

    /**
    * 分页查询
    */
    @RequestMapping("/pageList")
    @ResponseBody
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return userinfoService.pageList(offset, pagesize);
    }

}
