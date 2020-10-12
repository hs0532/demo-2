package com.haoshuang.sso.demosso.Mapper;

import com.haoshuang.sso.demosso.bean.People;
import com.haoshuang.sso.demosso.bean.PeopleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PeopleMapper {
    long countByExample(PeopleExample example);

    int deleteByExample(PeopleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(People record);

    int insertSelective(People record);

    List<People> selectByExample(PeopleExample example);

    People selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") People record, @Param("example") PeopleExample example);

    int updateByExample(@Param("record") People record, @Param("example") PeopleExample example);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKey(People record);
}