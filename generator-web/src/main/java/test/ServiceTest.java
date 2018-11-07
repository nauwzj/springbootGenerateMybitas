package test;

import com.softdev.system.generator.entity.ClassInfo;
import com.softdev.system.generator.util.CodeGeneratorTool;
import com.softdev.system.generator.util.FreemarkerTool;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huangph
 * @date 2018-10-26 11:16:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(ServiceTest.class);

    private static final String TEMPLATE_PATH = "generator-web/src/main/java/com/freemarker/hello/templates";
    private static final String CLASS_PATH = "generator-web/src/main/java/com/freemarker/hello";

    private static final String TABLE_SQL = "CREATE TABLE `push_task` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `push_batch` varchar(50) DEFAULT NULL COMMENT '推送批次',\n" +
            "  `create_at` datetime NOT NULL COMMENT '创建时间',\n" +
            "  `update_at` datetime DEFAULT NULL COMMENT '更新时间',\n" +
            "  `content` varchar(1024) DEFAULT NULL COMMENT '推送内容',\n" +
            "  `push_target` int(2) DEFAULT NULL COMMENT '推送客户 1-所有人 2-客户状态 3-手动上传',\n" +
            "  `push_at` datetime DEFAULT NULL COMMENT '推送时间',\n" +
            "  `push_tool` int(2) DEFAULT NULL COMMENT '推送工具 1-push 2-短信',\n" +
            "  `status` int(2) DEFAULT NULL COMMENT '推送状态 1-待推送 2-暂停 3-已推送',\n" +
            "  `push_way` int(2) DEFAULT NULL COMMENT '推送方式，1-立即 2-延迟推送',\n" +
            "  `push_type` int(2) DEFAULT NULL COMMENT '推送类型 1- 系统 2 - 手动',\n" +
            "  `customer_status` varchar(30) DEFAULT NULL COMMENT '100, \"未认证\" 101, \"已认证人脸识别或身份证\" 102, \"已认证人身份证\" 103, \"待认证个人信息\" 104, \"待认证联系人\" 105, \"待认证运营商\" 106, \"待绑卡\" 107, \"待借款（已进行首次绑卡，但未提交过任何借款订单）\" 108, \"待借款（上一笔借款为审核失败但已过锁定期可再次借款）\" 8, \"借款在途\" 9, \"借款锁定期\" 10, \"待复借\"',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=1107 DEFAULT CHARSET=utf8;\n" +
            "\n";
    private static final String AUTHOR_NAME = "huangph";
    private static final String PACKAGE_NAME = "com.softdev.system.generator.huangph";

    @Autowired
    private FreemarkerTool freemarkerTool;

    @Test
    public void create() {
        Writer out = null;
        try {
            // parse table
            ClassInfo classInfo = CodeGeneratorTool.processTableIntoClassInfo(TABLE_SQL);
            // code genarete
            Map<String, Object> params = new HashMap<>();
            params.put("classInfo", classInfo);
            params.put("AUTHOR_NAME", AUTHOR_NAME);
            params.put("PACKAGE_NAME", PACKAGE_NAME);
            // result
            Map<String, String> result = new HashMap<>();
            result.put("controller", freemarkerTool.processString("xxl-code-generator/controller.ftl", params));
            //class1
            Configuration configuration = new Configuration();
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            Template template = configuration.getTemplate("xxl-code-generator/controller.ftl");
            File docFile = new File(CLASS_PATH + "\\" + "User.java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            template.process(result, out);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^" + AUTHOR_NAME + " 文件创建成功 !");
            /*result.put("service", freemarkerTool.processString("xxl-code-generator/service.ftl", params));
            result.put("service_impl", freemarkerTool.processString("xxl-code-generator/service_impl.ftl", params));
            result.put("dao", freemarkerTool.processString("xxl-code-generator/dao.ftl", params));
            result.put("mybatis", freemarkerTool.processString("xxl-code-generator/mybatis.ftl", params));
            result.put("model", freemarkerTool.processString("xxl-code-generator/model.ftl", params));
            result.put("entity", freemarkerTool.processString("xxl-code-generator/entity.ftl", params));
            result.put("swaggerui", freemarkerTool.processString("xxl-code-generator/swagger-ui.ftl", params));*/
        } catch (IOException | TemplateException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
