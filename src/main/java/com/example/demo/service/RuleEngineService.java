package com.example.demo.service;

import com.example.demo.model.QueryParam;

public interface RuleEngineService {
    void executeAddRule(QueryParam param) ;
    void executeRemoveRule(QueryParam param) ;
}
