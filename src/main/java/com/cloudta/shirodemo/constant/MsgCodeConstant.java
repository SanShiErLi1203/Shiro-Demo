package com.cloudta.shirodemo.constant;

/**
 * 状态码与消息常量
 */
public enum  MsgCodeConstant {
    SUCCESS_CODE(200),
    SUCCESS_MSG("success"),
    SUCCESS_LOGIN_MSG("login success"),

    FAIL_CODE(500),
    FALI_LOGIN_MSG("login fail"),
    NULL_ERR_MSG("null ponit error"),
    RUNTIME_ERR_MSG("runtime is occur error"),
    CLASSCAST_ERR_MSG("class cast is occur error"),
    IO_ERR_MSG("io stream is occur error"),
    METHOD_UNKNOWN_ERR_MSG("unknown method is occur error"),
    INDEX_OUT_ERR_MSG("IndexOutOfBoundsArray id occur error"),
    E400_ERR_MSG("400 error code"),
    E405_ERR_MSG("405 error code"),
    E406_ERR_MSG("406 error code"),
    E500_ERR_MSG("500 error code"),
    OTHER_ERR_MSG("other error");

    private Integer code;
    private String message;
    /**
     * 这里权限修饰符只能是private，以保证只在内部使用
     * @param code
     */
    MsgCodeConstant(Integer code){
        this.code = code;
    }

    MsgCodeConstant(String message){
        this.message = message;
    }


    public String MSG_VALUE(){
        return this.message;
    }

    public Integer CODE_VALUE(){
        return this.code;
    }
}
