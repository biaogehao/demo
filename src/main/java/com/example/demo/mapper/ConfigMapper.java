package com.example.demo.mapper;

import com.example.demo.model.Config;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by zhengb
 */
@Mapper
public interface ConfigMapper {

    @Select("select * from CONFIG")
    List<Config> queryAll();

    @Update("update CONFIG set value=#{value} WHERE `key`=#{key}")
    int update(Config config);

}
