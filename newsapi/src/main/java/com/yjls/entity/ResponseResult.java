package com.yjls.entity;

import java.io.Serializable;

public class ResponseResult implements Serializable {
    private String result;//返回标志
    private String resultMsg;//返回消息
    private String resultUrl = "jumpIndex";//返回资源

    public ResponseResult() {
    }

    public ResponseResult(String result, String resultMsg, String resultUrl) {
        this.result = result;
        this.resultMsg = resultMsg;
        this.resultUrl = resultUrl;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getResultUrl() {
        return resultUrl;
    }

    public void setResultUrl(String resultUrl) {
        this.resultUrl = resultUrl;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "result='" + result + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", resultUrl='" + resultUrl + '\'' +
                '}';
    }
}
