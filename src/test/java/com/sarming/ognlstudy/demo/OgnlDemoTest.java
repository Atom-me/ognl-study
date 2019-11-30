package com.sarming.ognlstudy.demo;

import com.sarming.ognlstudy.demo.entity.User;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * OGNL： Object-Graph Navigation Language 一种功能强大对表达式语言。
 * 可以存取对象对任意属性，调用对象对方法，遍历整个对象对结构图，实现字段类型转化功能。
 * OGNL 提供一个上下文OgnlContext 实现了Map接口
 * OGNL 三要素： expression,context，root
 * <p>
 * OGNL应用：
 * 1,Struts2 里面引入了ognl
 * 2,MyBatis对OGNL做了封装。
 * <p>
 * OGNL应用：https://www.jianshu.com/p/c5c284ccaadd
 */
class OgnlDemoTest {

    /**
     * OGNL 调用对象的方法
     */
    @Test
    void invokeObjectMethod() throws OgnlException {
        // 获得context
        OgnlContext context = new OgnlContext();
        //获得根对象
        Object root = context.getRoot();
        //执行表达式
        Object value = Ognl.getValue("'helloworld'.length()", context, root);
        System.out.println(value);
    }

    /**
     * OGNL 调用对象的静态方法
     */
    @Test
    public void invokeStaticMethod() throws OgnlException {
        // 获得context
        OgnlContext context = new OgnlContext();
        //获得根对象
        Object root = context.getRoot();
        //执行表达式 ，访问静态方法有固定的格式：@类名@静态方法名
        Object value = Ognl.getValue("@java.lang.Math@random()", context, root);
        System.out.println(value);
    }

    /**
     * OGNL 访问Root中的数据,获得的是root中的数据，不需要加#号，
     * 获取context中的数据需要加#号
     */
    @Test
    public void getValueFromRoot() throws OgnlException {
        // 获得context
        OgnlContext context = new OgnlContext();
        // 往root里面放数据
        context.setRoot(User.builder().name("liuyaming").age(18).build());
        //获得根对象,一定要先setRoot
        Object root = context.getRoot();
        //执行表达式 ，访问Root中的数据
        Object name = Ognl.getValue("name", context, root);
        Object age = Ognl.getValue("age", context, root);
        System.out.println(name + "\t" + age);
    }

    /**
     * OGNL 访问Root中的数据,获得的是root中的数据，不需要加#号，
     * 获取context中的数据需要加#号
     */
    @Test
    public void getValueFromContext() throws OgnlException {
        // 获得context
        OgnlContext context = new OgnlContext();
        // 往context里面放数据
        context.put("name", "zhangsan");
        context.put("age", 19);
        Object root = context.getRoot();
        //执行表达式 ，访问Root中的数据
        Object name = Ognl.getValue("#name", context, root);
        Object age = Ognl.getValue("#age", context, root);
        System.out.println(name + "\t" + age);
    }


    @Test
    public void testGetValue() throws OgnlException {
        User user = new User();
        user.setId(1);
        user.setName("atom");

        Map context = new HashMap(3);
        context.put("name", "liuyaming001");
        context.put("age", 99);
        context.put("introduction", "my name is ");

        // Ognl.getValue 这个重载了多个方法，如果只传表达式表示只从root里面取，下面使用#号表示从context里面取，所以下面的带#号的娶不到数据。
        Object nameInRoot = Ognl.getValue(Ognl.parseExpression("name"), user);
        Object nameInContext = Ognl.getValue(Ognl.parseExpression("#name"), user);
        System.out.println(nameInRoot);
        System.out.println(nameInContext);
        System.out.println("==========================");

        // 不加#号表示从root里面取
        Object nameInRoot2 = Ognl.getValue(Ognl.parseExpression("name"), context, user);
        //加#号表示从context里面取
        Object nameInContext2 = Ognl.getValue(Ognl.parseExpression("#name"), context, user);
        System.out.println(nameInRoot2);
        System.out.println(nameInContext2);
        System.out.println("==========================");

        Object value = Ognl.getValue(Ognl.parseExpression("#introduction"), context, user);
        System.out.println(value);

        Object value1 = Ognl.getValue(Ognl.parseExpression("#introduction + name"), context, user);
        System.out.println(value1);


    }

}