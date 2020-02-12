package com.example.demo.mapper;


import com.example.demo.model.BlackList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * Created by zhengb
 */
@Mapper
public interface BlackListMapper {

    @Select("select * from BLACK_LIST")
    List<BlackList> queryAll();

    @Insert("insert into BLACK_LIST(dimension,type,value,detail) VALUES (#{dimension},#{type},#{value},#{detail})")
    int add(BlackList blackList);

    @Select("select * from BLACK_LIST where dimension=#{dimension} and value= #{value}")
    BlackList query(BlackList blackList);

}
