package com.yjls.util;

import org.springframework.util.DigestUtils;

import java.util.UUID;

public class StringUtil {
    /**
     * 判断字符串
     * @param str
     * @return String
     */
    public static boolean isEmpty(String str){
        if(null==str||"".equals(str)){
            return true;
        }
        return false;
    }

    /**
     * 返回目标字符串的MD5加密模式
     * @param targetStr
     * @return
     */
    public static String getMD5Transfer(String targetStr){
        if(!isEmpty(targetStr)){
            return DigestUtils.md5DigestAsHex(targetStr.trim().getBytes());
        }
        return targetStr;
    }

    /**
     * 生成UUID并删除其中“-”作为字符串返回
     * @return  String
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().trim().replaceAll("-","");
    }
}
