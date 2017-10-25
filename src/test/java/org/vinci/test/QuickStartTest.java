package org.vinci.test;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vinci.test.reference.WordUtil;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by XizeTian on 2017/10/25.
 */
public class QuickStartTest {
    VelocityEngine  ve       = new VelocityEngine();
    VelocityContext context  = new VelocityContext();
    Template        template = null;

    /**
     * hello velocity
     * 初始化: @Before
     * 后置处理: @After
     */
    @Test
    public void first() {
        context.put("name", "Velocity 你好");
        template = ve.getTemplate("templates/first.vm");
    }

    /**
     * 导入静态类
     */
    @Test
    public void staticClass() {
        context.put("name", "Velocity 你好");
        context.put("WordUtil", WordUtil.class);
        template = ve.getTemplate("templates/static-class.vm");
    }

    /**
     * 模板上下文链接
     */
    @Test
    public void contextChaining() {
        context = new VelocityContext();
        context.put("name", "vinci");
        VelocityContext context1 = new VelocityContext(context);
        context1.put("birthday", "2016-06-04");
        context = context1;
        template = ve.getTemplate("templates/context-chaining.vm");
    }

    /****************************************************************before and after***************************************************************/
    @Before
    public void before() {
        Velocity.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        Velocity.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        ve.setProperty("resource.loader", "class");
        ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        ve.init();
    }

    @After
    public void after() throws IOException {
        StringWriter sw = new StringWriter();
        template.merge(context, sw);
        if (sw != null) {
            sw.close();
        }
        System.out.println(sw.toString());
    }
}