package com.example.demo.controller;


import com.example.demo.model.QueryParam;
import com.example.demo.model.RuleResult;
import com.example.demo.service.RuleEngineService;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rule")
public class RuleController {

    @Resource
    private KieSession kieSession;
    @Resource
    private RuleEngineService ruleEngineService ;

    @RequestMapping("/param")
    public void param (String id){
        QueryParam queryParam1 = new QueryParam() ;
        queryParam1.setParamId(id);
        queryParam1.setParamSign("+");
//        QueryParam queryParam2 = new QueryParam() ;
//        queryParam2.setParamId("2");
//        queryParam2.setParamSign("-");
        // 入参
        kieSession.insert(queryParam1) ;
     //  kieSession.insert(queryParam2) ;
       // kieSession.insert(this.ruleEngineService) ;

        // 返参
        //RuleResult resultParam = new RuleResult() ;
       // kieSession.insert(resultParam) ;
       kieSession.fireAllRules() ;
    }


    @RequestMapping("/paramd")
    public void paramd (String id){

        QueryParam queryParam2 = new QueryParam() ;
        queryParam2.setParamId(id);
        queryParam2.setParamSign("-");
        // 入参
        kieSession.insert(queryParam2) ;
        kieSession.insert(this.ruleEngineService) ;

        // 返参
       RuleResult resultParam = new RuleResult() ;
        kieSession.insert(resultParam) ;
        kieSession.fireAllRules() ;
    }




}
