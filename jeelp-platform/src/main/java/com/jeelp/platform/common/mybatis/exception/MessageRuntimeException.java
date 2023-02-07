package com.jeelp.platform.common.mybatis.exception;

/**
 * 包含业务实体的运行时消息异常
 */

public class MessageRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 引发异常的消息实体
     */
    private Object entity = null;

    /**
     * 构造包含异常信息，业务实体的运行时消息异常
     *
     * @param message 异常信息
     * @param entity     业务实体对象
     */
    public MessageRuntimeException(String message, Object entity) {
        super(message);
        this.entity = entity;
    }

    /**
     * 构造包含异常信息，业务实体及嵌套异常运行时消息异常
     * 
     * @param message 异常信息
     * @param cause   导致此异常被抛出的嵌套异常
     * @param entity  业务实体对象
     */
    public MessageRuntimeException(String message, Throwable cause, Object entity) {
        super(message, cause);
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

}