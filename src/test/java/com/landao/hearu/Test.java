package com.landao.hearu;

import org.apache.catalina.connector.CoyoteAdapter;

import java.util.Properties;

public class Test {

    public static void main(String[] args) {
        CoyoteAdapter coyoteAdapter;
        System.out.println(System.getProperty("landao.key"));
        Properties properties = System.getProperties();
    }

}
