package com.example.demo.model;

/**
 * 运行时异常
 * Created by zhengb
 */
public class RCRuntimeException extends RuntimeException {

    private String id;

    public RCRuntimeException() {
        super();
    }

    public RCRuntimeException(String id) {
        super(CodeMap.getMsgbyCode(id));
        this.id = id;
    }

    public RCRuntimeException(String id, String message) {
        super(message);
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
