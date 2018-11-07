package com.softdev.system.generator.controller;

import com.softdev.system.generator.entity.ClassInfo;
import com.softdev.system.generator.entity.CreateInfo;
import com.softdev.system.generator.entity.ReturnT;
import com.softdev.system.generator.util.CodeGeneratorTool;
import com.softdev.system.generator.util.FreemarkerTool;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * spring boot code generator
 * @author zhengk/moshow
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    private static final String PATH_CONTROLLER = "generator-web/src/main/java/com/huangph/controller";
    private static final String PATH_SERVICE = "generator-web/src/main/java/com/huangph/service";
    private static final String PATH_SERVICE_IMPL = "generator-web/src/main/java/com/huangph/service/impl";
    private static final String PATH_DAO = "generator-web/src/main/java/com/huangph/dao";
    private static final String PATH_MAPPING = "generator-web/src/main/java/com/huangph/mapping";
    private static final String PATH_ENTITY = "generator-web/src/main/java/com/huangph/entity";

    @Autowired
    private FreemarkerTool freemarkerTool;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/genCode")
    @ResponseBody
    public ReturnT<Map<String, String>> codeGenerate(String tableSql,String authorName,String packageName) {

        if(StringUtils.isBlank(authorName)) authorName="huangph";

        if(StringUtils.isBlank(packageName)) packageName="com.huangph";

        try {

            if (StringUtils.isBlank(tableSql)) {
                return new ReturnT<Map<String, String>>(ReturnT.FAIL_CODE, "表结构信息不可为空");
            }

            // parse table
            ClassInfo classInfo = CodeGeneratorTool.processTableIntoClassInfo(tableSql);

            // code genarete
            CreateInfo createInfo = new CreateInfo();
            createInfo.setClassInfo(classInfo);
            createInfo.setAuthorName(authorName);
            createInfo.setPackageName(packageName);

            // result
            Map<String, String> result = new HashMap<String, String>();

            result.put("controller", freemarkerTool.processFileAndString("xxl-code-generator/controller.ftl",
                    createInfo, PATH_CONTROLLER, classInfo.getClassName()+"Controller.java"));
            result.put("service", freemarkerTool.processFileAndString("xxl-code-generator/service.ftl",
                    createInfo, PATH_SERVICE, classInfo.getClassName()+"Service.java"));
            result.put("service_impl", freemarkerTool.processFileAndString("xxl-code-generator/service_impl.ftl",
                    createInfo, PATH_SERVICE_IMPL, classInfo.getClassName()+"ServiceImpl.java"));
            result.put("dao", freemarkerTool.processFileAndString("xxl-code-generator/dao.ftl",
                    createInfo, PATH_DAO, classInfo.getClassName()+"Dao.java"));
            result.put("mybatis", freemarkerTool.processFileAndString("xxl-code-generator/mybatis.ftl",
                    createInfo, PATH_MAPPING, classInfo.getClassName() + "Mapper.xml"));
            result.put("beetlentity", freemarkerTool.processFileAndString("xxl-code-generator/beetlentity.ftl",
                    createInfo, PATH_ENTITY, classInfo.getClassName()+".java"));
            // 计算,生成代码行数
            int lineNum = 0;
            for (Map.Entry<String, String> item: result.entrySet()) {
                if (item.getValue() != null) {
                    lineNum += StringUtils.countMatches(item.getValue(), "\n");
                }
            }
            logger.info("生成代码行数：{}", lineNum);
            logger.info("生成代码数据：{}", result);
            return new ReturnT<>(result);
        } catch (IOException | TemplateException e) {
            logger.error(e.getMessage(), e);
            return new ReturnT<>(ReturnT.FAIL_CODE, "表结构解析失败"+e.getMessage());
        }

    }

}