package com.example.demo.controller;

import com.example.demo.model.BlackList;
import com.example.demo.model.CodeMap;
import com.example.demo.model.RCRuntimeException;
import com.example.demo.model.Result;
import com.example.demo.service.BlackListService;
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
@RequestMapping("/blacklist")
public class BlackListController {

    private static Logger logger = LoggerFactory.getLogger(BlackListController.class);

    @Autowired
    private BlackListService blackListService;

    @RequestMapping(path = "/queryall", method = RequestMethod.GET)
    public Result<List<BlackList>> queryAll() {
        Result r = Result.success();
        try {
            r.setData(blackListService.queryAll());
        } catch (RCRuntimeException e) {
            r = Result.fail();
            r.setRetCode(e.getId());
        } catch (Exception e) {
            r = Result.fail();
        }
        return r;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Result add(String dimension, String type, String value, String detail) {
        Result r = Result.success();
        try {
            if (StringUtils.isEmpty(dimension) || StringUtils.isEmpty(type) || StringUtils.isEmpty(value)) {
                throw new RCRuntimeException(CodeMap.PARAM_ERROR);
            }

            BlackList.EnumDimension enumDimension = BlackList.EnumDimension.valueOf(dimension.toUpperCase());
            if (enumDimension == null) {
                throw new RCRuntimeException(CodeMap.PARAM_ERROR);
            }
            BlackList.EnumType enumType = BlackList.EnumType.valueOf(type.toUpperCase());
            if (enumType == null) {
                throw new RCRuntimeException(CodeMap.PARAM_ERROR);
            }

            BlackList blackList = new BlackList();
            blackList.setDimension(enumDimension);
            blackList.setType(enumType);
            blackList.setValue(value);
            blackList.setDetail(detail);
            blackListService.pub(blackList);
        } catch (RCRuntimeException e) {
            r = Result.fail();
            r.setRetCode(e.getId());
        } catch (Exception e) {
            logger.error("添加黑名单失败.", e);
            r = Result.fail();
        }
        return r;
    }

}
