package com.yjls.entity;

import java.io.Serializable;

public class NewsFinal implements Serializable {

    //0开头的,系统的通用的结果码
    public static final String SUCCESS_RESULT = "0000";
    public static final String FILED_RESULT = "0001";

    //1开头的,用户的操作的结果码
    public static final String NAME_NULL_RESULT = "1001";

    //登录信息异常
    public static final String Login_ERROR = "1002";

    //用户账号唯一性
    public static final String Unique_Value = "1011";
    public static final String Not_Unique_Value = "1012";

    //2开头的,角色操作的结果码
    public static final String ROLE_ADD_FILED_RESULT = "2001";

    //数字
    public static final int NUM_ZERO = 0;
    public static final int NUM_ONE = 1;
    public static final int NUM_TWO = 2;
    public static final int NUM_THREE = 3;
    public static final int NUM_FOUR = 4;
    public static final int NUM_FIVE = 5;
    public static final int NUM_EIGHT = 8;
    public static final int NUM_TWELVE = 12;

    //价格
    public static final float NUM_ZEROF = 0.00F;

    //地址
    public static final String  ADDRESS_COUNTRY_CHINA = "中国";
}
