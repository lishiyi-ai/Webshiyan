package org.example.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.pojo.User;

public interface UserMapper {
    @Select("select * from u_user where name=#{name} and password=#{password}")
    User login(User user);
}
