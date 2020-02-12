package com.example.demo.controller;


import com.example.demo.model.*;
import com.example.demo.service.BlackListService;
import com.example.demo.service.ConfigService;
import com.example.demo.service.DimensionService;
import com.example.demo.util.EventFactory;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zhengb
 */
@RestController
@RequestMapping("/riskcontrol")
public class RiskController {

    private static Logger logger = LoggerFactory.getLogger(RiskController.class);

//    @Autowired
//    private KieService kieService;
    @Resource
    private KieSession kieSession;

    @Resource
    private KieBase kieBase;

    @Autowired
    private ConfigService configService;

    @Autowired
    private DimensionService dimensionService;

    @Autowired
    private BlackListService blackListService;

    @RequestMapping(value = "/req", method = RequestMethod.POST)
    public Result<String> req(String json) {
        Result r = Result.success();
        try {
            if (StringUtils.isEmpty(json)) {
                throw new RCRuntimeException(CodeMap.PARAM_ERROR);
            }

            Event event = EventFactory.build(json);
            if ("ON".equals(configService.query("SWITCH_RC"))) {

                kieSession.setGlobal("blackListService", blackListService);
                kieSession.setGlobal("dimensionService", dimensionService);
                kieSession.insert(event);
                int ruleFiredCount = kieSession.fireAllRules();
                //
                // kieSession.

                logger.info(" " + ruleFiredCount + "条规则");
               // kieService.execute(event);
            }
            r.setData(event);
        } catch (RCRuntimeException e) {
            r = Result.fail();
            r.setRetCode(e.getId());
        } catch (Exception e) {
            logger.error("业务风控分析失败", e);
            r = Result.fail();
        }
        return r;
    }
}
