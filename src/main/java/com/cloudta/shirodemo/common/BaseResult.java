package com.cloudta.shirodemo.common;

import java.util.HashMap;
import java.util.Map;

/**
 * JSON数据的返回结果
 */
public class BaseResult {

    private Integer code;       //状态码，200为成功，其他为失败
    private String message;     //消息，success为成功，其他为失败消息
    private Object data;        //结果集

    private static final String AFTER_DATA = "after";
    private static final String BEFORE_DATA = "before";

    private BaseResult(){};

    /**
     * 状态码为200，成功的结果集
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static BaseResult jsonData(Integer code, String message, Object data){
        BaseResult baseResult = new BaseResult();
        baseResult.code = code;
        baseResult.message = message;
        baseResult.data = data;
        return baseResult;
    }

    /**
     *
     * @param code
     * @param message
     * @param before
     * @param after
     * @return
     */
    public static BaseResult jsonData(Integer code, String message, Object before, Object after){
        BaseResult baseResult = new BaseResult();
        baseResult.code = code;
        baseResult.message = message;
        Map<Object, Object> dataMap = new HashMap<>();
        dataMap.put(BEFORE_DATA, before);
        dataMap.put(AFTER_DATA, after);
        baseResult.data = dataMap;
        return baseResult;
    }

    /**
     * 状态码不为200时，出错的结果集
     * @param code
     * @param message
     * @return
     */
    public  static BaseResult jsonData(Integer code, String message){
        return BaseResult.jsonData(code, message, null);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
