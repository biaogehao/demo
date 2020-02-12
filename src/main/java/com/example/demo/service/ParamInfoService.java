package com.example.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.ParamInfo;

public interface ParamInfoService extends IService<ParamInfo> {
    ParamInfo selectById(String paramId) ;
    void insertParam(ParamInfo paramInfo) ;
}
