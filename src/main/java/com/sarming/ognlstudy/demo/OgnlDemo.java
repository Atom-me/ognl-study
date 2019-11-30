package com.sarming.ognlstudy.demo;

import com.sarming.ognlstudy.demo.entity.User;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import java.util.HashMap;
import java.util.Map;

public class OgnlDemo {
    public static void main(String[] args) throws OgnlException {

//        testGetValue();
//        testSetValue();

    }

    private static void testSetValue() throws OgnlException {
        User user = new User();
        user.setId(2);
        user.setName("sarming");

        Ognl.setValue("group.name", user, "dev");
        Ognl.setValue("age", user, 18);
        System.out.println(user.getGroup().getName());

    }


    public static void testGetValue() throws OgnlException {
        User user = new User();
        user.setId(1);
        user.setName("atom");

        Map context = new HashMap(3);
        context.put("introduction", "my name is ");

        Object name = Ognl.getValue(Ognl.parseExpression("name"), user);
        System.out.println(name);


        Object value = Ognl.getValue(Ognl.parseExpression("#introduction"), context, user);
        System.out.println(value);

        Object value1 = Ognl.getValue(Ognl.parseExpression("#introduction + name"), context, user);
        System.out.println(value1);


    }




}
