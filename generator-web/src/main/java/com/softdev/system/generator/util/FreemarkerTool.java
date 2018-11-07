package com.softdev.system.generator.util;

import com.softdev.system.generator.entity.CreateInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;

/**
 * freemarker tool
 *
 * @author xuxueli 2018-05-02 19:56:00
 */
@Component
public class FreemarkerTool {
    private static final Logger logger = LoggerFactory.getLogger(CodeGeneratorTool.class);

    @Autowired
    private Configuration configuration;

    /**
     * process Template Into String
     *
     * @param template
     * @param model
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public String processTemplateIntoString(Template template, Object model)
            throws IOException, TemplateException {

        StringWriter result = new StringWriter();
        template.process(model, result);
        return result.toString();
    }

    /**
     * process String
     *
     * @param templateName
     * @param params
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public String processString(String templateName, Map<String, Object> params)
            throws IOException, TemplateException {

        Template template = configuration.getTemplate(templateName);
        String htmlText = processTemplateIntoString(template, params);
        return htmlText;
    }

    public String processFileAndString(String templateName, CreateInfo createInfo, String path, String fileName)
            throws IOException, TemplateException {
        Template template = configuration.getTemplate(templateName);
        //生成html
        String htmlText = processTemplateIntoString(template, createInfo);
        Writer out = null;
        try {
            File docFile = new File(path+ "\\" + fileName);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            template.process(createInfo, out);
            logger.info("输出文件成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return htmlText;
    }


}
