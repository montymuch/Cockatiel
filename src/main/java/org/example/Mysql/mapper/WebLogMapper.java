package org.example.Mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.record.WebLog;

@Mapper
public interface  WebLogMapper {

    public void addWebLog(WebLog webLog);;
}
