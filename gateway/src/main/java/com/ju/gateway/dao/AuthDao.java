package com.ju.gateway.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AuthDao {

    @Select("select permission_url from \n" +
            "permission_info pi, \n" +
            "role_permission_relation rpr, \n" +
            "user_info ui,\n" +
            "user_role_relation urr \n" +
            "where \n" +
            "pi.permission_id = rpr.url_id and \n" +
            "urr.role_id  = rpr.role_id and \n" +
            "ui.id = urr.user_id \n" +
            "and ui.id = #{userId}")
    public List<String> getAuths(@Param("userId") int userId);

}
