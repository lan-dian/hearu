package com.landao.hearu.util.check.enums;


public enum CheckType {

    /**
     * 根据类型和BaseCheck自动判断
     */
    Auto,
    /**
     * 数字不能为负数
     */
    NotNegative,
    /**
     * 字符串长度限制
     */
    LengthLimit,
    /**
     * 没有检查
     */
    NoCheck

}
