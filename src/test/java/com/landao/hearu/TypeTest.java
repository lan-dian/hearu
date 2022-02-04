package com.landao.hearu;

import java.util.Objects;

public class TypeTest {

    public static void main(String[] args) {
        Long num=1234L;

        sout(num);

    }

    public static void sout(Object obj){
        Class<?> aClass = obj.getClass();
        if(Objects.equals(aClass,Long.class) || Objects.equals(aClass,long.class)){
            Long obj1 = (Long) obj;
            System.out.println(obj1);
        }

    }

}
