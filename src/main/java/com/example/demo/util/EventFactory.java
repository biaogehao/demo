package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.EnumScene;
import com.example.demo.model.Event;
import com.example.demo.model.LoginEvent;
import com.example.demo.model.RCRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Created by zhengb
 */
@Component
public class EventFactory {

    private static Logger logger = LoggerFactory.getLogger(EventFactory.class);


    public static Event build(String json) {
        String scene = JSON.parseObject(json).getString("scene");
        if (StringUtils.isEmpty(scene)) {
            logger.error("scene参数错误，" + json);
            throw new RCRuntimeException("scene参数确实，" + json);
        }
        EnumScene enumScene = EnumScene.valueOf(scene.toUpperCase());
        if (enumScene == null) {
            logger.error("json格式错误，" + json);
            throw new RCRuntimeException("json格式错误，" + json);
        }

        Event event = null;
        if (EnumScene.LOGIN.equals(enumScene)) {
            event = JSON.parseObject(json, LoginEvent.class);
        }

        check(event);

        return event;
    }


    private static void check(Event event) {
        if (event == null) {
            throw new RCRuntimeException("事件错误");
        }
        if (event.getOperateTime() == null) {
            event.setOperateTime(new Date());
        }
        if (StringUtils.isEmpty(event.getId())) {
            event.setId(UUID.randomUUID().toString());
        }

        // TODO  扩展维度信息
    }

}
