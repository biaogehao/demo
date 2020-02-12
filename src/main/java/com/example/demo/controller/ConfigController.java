package com.example.demo.controller;


import com.example.demo.model.CodeMap;
import com.example.demo.model.Config;
import com.example.demo.model.RCRuntimeException;
import com.example.demo.model.Result;
import com.example.demo.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhengb
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    private static Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private ConfigService configService;

    @RequestMapping(value = "/queryall", method = RequestMethod.GET)
    public Result<List<Config>> query() {
        Result r = Result.success();
        try {
            List<Config> configs = configService.queryAll();
            r.setData(configs);
        } catch (RCRuntimeException e) {
            r = Result.fail();
            r.setRetCode(e.getId());
        } catch (Exception e) {
            logger.error("查询配置失败", e);
            r = Result.fail();
        }
        return r;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public Result update(String key, String value) {
        Result r = Result.success();
        try {
            if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
                throw new RCRuntimeException(CodeMap.PARAM_ERROR);
            }

            Config config = new Config();
            config.setKey(key);
            config.setValue(value);
            configService.pub(config);
        } catch (RCRuntimeException e) {
            r = Result.fail();
            r.setRetCode(e.getId());
        } catch (Exception e) {
            logger.error("配置更新失败", e);
            r = Result.fail();
        }
        return r;
    }

}
